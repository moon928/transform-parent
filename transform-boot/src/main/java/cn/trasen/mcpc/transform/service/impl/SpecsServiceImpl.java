package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.util.IdWorker;
import cn.trasen.mcpc.transform.dao.TmpFactoryMapper;
import cn.trasen.mcpc.transform.dao.TmpYjjDataMapper;
import cn.trasen.mcpc.transform.dto.TmpDictionaryDto;
import cn.trasen.mcpc.transform.dto.TmpFactoryDto;
import cn.trasen.mcpc.transform.dto.UscDrgSpecsDto;
import cn.trasen.mcpc.transform.model.*;
import cn.trasen.mcpc.transform.service.SpecsService;
import cn.trasen.mcpc.transform.service.TTestService;
import cn.trasen.mcpc.transform.service.UscDictDetailService;
import cn.trasen.mcpc.transform.util.ReadExcelUtil;
import cn.trasen.mcpc.transform.util.WriteFile;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: project-parent
 * @description: SpecsService实现类
 * @author: yan_zt
 * @create: 2020-06-23 16:32
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SpecsServiceImpl extends BaseServiceImpl<UscDrgSpecs> implements SpecsService {


    @Autowired
    private TmpFactoryMapper factoryMapper;

    private Map<String,String> detailMap = new HashMap<>();

    /**
     * 进口药品临时map
     */
    private Map<String,Long> jkypMap = new HashMap<String,Long>();


    private Map<String,Long> factMap = new HashMap<String,Long>();

    @Autowired
    private UscDictDetailService uscDictDetailService;

    @Autowired
    private TmpYjjDataMapper yjjDataMapper;

    //分页条数
    public static final int PAGE_SIZE = 20000;
    //剂型 字典
    String dosform = "DOSFORM";
    //药品类型字典
    String drugType = "drug_type";
    @Override
    public Mapper<UscDrgSpecs> getMapper() {
        return null;
    }

    void initializeFactory(){
        List<TmpFactory> list = factoryMapper.all();
        for (TmpFactory item : list){
            factMap.put(item.getFactName(),item.getId());
        }
    }

    void initializeDetail(){
        List<UscDictDetail> list = uscDictDetailService.list(null);
        for (UscDictDetail uscDictDetail : list){

            detailMap.put(uscDictDetail.getName()+uscDictDetail.getCatalogCode(),uscDictDetail.getCode());
        }
    }


    /**
     * 判断企业名称是否存在
     * @param name
     * @return
     */
    public Long isHaveFact(String name){
        if (detailMap.size() == 0){
            initializeDetail();
        }
        Long id = factMap.get(name);
        return id;
    }

    /**
     * 判断剂型是否在存在字典中
     * @param name 名称
     * @param catalogCode 字典类型 剂型-DOSFORM  药品类型-drug_type
     * @return
     */
    public String isHave(String name,String catalogCode){
        if (detailMap.size() == 0){
            initializeDetail();
        }
        String s = detailMap.get(name+catalogCode);
        return s;
    }


    @Override
    public String test() {
        String have1 = isHave("片剂", "DOSFORM");
        String have2 = isHave("片剂aa", "DOSFORM");
        String have3 = isHave("西药", "drug_type");
        System.out.println(have1);
        System.out.println(have2);
        System.out.println(have3);
        return "ok";
    }

    @Override
    public String jkypToSql(String excelPath, String sqlPath) {
        Map<String, List<UscDrgSpecsDto>> map = ReadExcelUtil.readXlsxForjkyp(excelPath);
        List<UscDrgSpecsDto> success = map.get("success");
        //有问题的不插入
        List<UscDrgSpecsDto> notInsertDrug = new ArrayList<UscDrgSpecsDto>();
        List<UscDrgSpecsDto> insertDrug = new ArrayList<UscDrgSpecsDto>();
        Map<String, List<UscDrgSpecsDto>> drugData = new HashMap<String, List<UscDrgSpecsDto>>();
        for (UscDrgSpecsDto item: success){
            //标记 0-没有错误 1-有错误
            int flag = 0;
            String readError = "";

            //是否进口
            item.setImported(1);
            //剂型 字典
            String jxCode = isHave(item.getDrugDosformCode(), dosform);
            if (StringUtils.isNotBlank(jxCode)){
                item.setDrugDosformCode(jxCode);
            }else {
                readError += "--剂型" + item.getDrugDosformCode() + "字典中无匹配项";
                flag = 1;
            }
            //药品类型 字典
            String lxCode = isHave(item.getDrugType(), drugType);
            if (StringUtils.isNotBlank(lxCode)){
                item.setDrugType(lxCode);
            }else {
                readError += "--药品类型" + item.getDrugType() + "字典中无匹配项";
                flag = 1;
            }
            if (flag==1){
                item.setReadError(readError);
                notInsertDrug.add(item);
                continue;
            }
//            if (jkypMap.get(item.getChemicalName()+item.getDrugDosformCode()+item.getDrugType()) != null){
//                continue;
//            }else{
//                item.setId(IdWorker.getInstance().getId());
//                insertDrug.add(item);
//                jkypMap.put(item.getChemicalName()+item.getDrugDosformCode()+item.getDrugType(),item.getId());
//            }
        }
        String drugSql = sqlPath + "drugSuccess.txt";
        writeSpecs(insertDrug,drugSql);
        //错误信息先写入json，在用网页的工具将json转为excel
        String drugErrorJson = sqlPath + "drugError.json";
        writeSpecsError(notInsertDrug,drugErrorJson);
        return "ok";
    }

    public void writeSpecs(List<UscDrgSpecsDto> drugData,String filePath){

        String sql = "";
        for (UscDrgSpecsDto item :drugData){
            sql += String.format("INSERT INTO tmp_specs (ID,STANDARD_CODE,CHEMICAL_NAME,DRUG_DOSFORM_CODE," +
                            "DRUG_TYPE,SPEC_DESC,VERSION_ID,IMPORTED)" +
                            "VALUES ('%s', '%s', '%s','%s','%s','%s','%s','%s');\n",
                    item.getId(),item.getStandardCode(),item.getChemicalName(),item.getDrugDosformCode(),item.getDrugType(),
                    item.getSpecDesc(),item.getVersionId(),item.getImported());
        }
        WriteFile.writeToFile(filePath,sql);
        System.out.println("写入药品规格数据完成");
    }

    public void writeFact(List<TmpFactoryDto> factData,String filePath){
        String sql = "";
        for (TmpFactoryDto item :factData){
            sql += String.format("INSERT INTO tmp_factory (ID,FACT_NAME,REL_ADDR)" +
                            "VALUES ('%s', '%s', '%s');\n",
                    item.getId(),item.getFactName(),item.getRelAddr());
        }
        WriteFile.writeToFile(filePath,sql);
        System.out.println("写入企业数据完成");
    }

    public void writeDict(List<TmpDictionaryDto> dictData,String filePath){
        String sql = "";
        for (TmpDictionaryDto item :dictData){
            sql += String.format("INSERT INTO tmp_dictionary (ID,SPECID,COMNAME,DRUGCODE_SPDA,DRUGCODE_HPC,APP_CODE,FACT_ID)" +
                            "VALUES ('%s', '%s', '%s','%s', '%s', '%s','%s');\n",
                    item.getId(),item.getSpecid(),item.getComname(),item.getDrugcodeSpda(),item.getDrugcodeHpc(),item.getAppCode(),item.getFactId());
        }
        WriteFile.writeToFile(filePath,sql);
        System.out.println("写入药品厂商数据完成");
    }

    public void writeSpecsError(List<UscDrgSpecsDto> notInsertDrug,String filePath){

        String json = "[";
        for (UscDrgSpecsDto item :notInsertDrug){
            json += JSON.toJSONString(item) + ",\n";
        }
        json = json.substring(0,json.length() - 2);
        json += "]";
        WriteFile.writeToFile(filePath,json);
        System.out.println("写入药品规格-错误-数据完成");
    }

    @Override
    public String jkypSpecs(String type) {
//        Page page = new Page();
//        page.setPageNo(1);
//        page.setPageSize(5000);
        int currSize = PAGE_SIZE;
        int pageIndex = 1;
        Page page = new Page();
        page.setPageSize(PAGE_SIZE);
        Long staCode = 100000L;
        while (currSize == PAGE_SIZE){
            page.setPageNo(pageIndex++);

            List<TmpYjjData> list = yjjDataMapper.page(page, type);
            //需要插入的企业表
            List<TmpFactoryDto> insertFact = new ArrayList<TmpFactoryDto>();
            //需要插入的药品规格表
            List<UscDrgSpecsDto> insertDrug = new ArrayList<UscDrgSpecsDto>();
            //有问题的药品规格表
            List<UscDrgSpecsDto> notInsertDrug = new ArrayList<UscDrgSpecsDto>();
            //需要插入的药品厂家表
            List<TmpDictionaryDto> insertDict = new ArrayList<TmpDictionaryDto>();

            for (int i = 0; i<list.size();i++){
                TmpYjjData item = list.get(i);

                // 药品企业
                TmpFactoryDto tmpFactoryDto = handleFactory(item.getFactName());
                if (StringUtils.isNotBlank(item.getFactName())){

                    tmpFactoryDto.setFactName(item.getFactName().trim());
                    tmpFactoryDto.setRelAddr(item.getRelAddr());
                    if (tmpFactoryDto.getIsHave()==1){
                        if (item.getFactName().equals("----")){

                        }else{
                            insertFact.add(tmpFactoryDto);
                        }
                    }
                    tmpFactoryDto.setFactName(item.getFactName().trim());
                    tmpFactoryDto.setRelAddr(item.getRelAddr());
                }else{
                    tmpFactoryDto.setIsHave(-1L);
                }

                // 药品规格
                UscDrgSpecsDto drugDto = handleSpecs(item);
                if (type.equals("0")){
                    drugDto.setDomestic(1);
                }else{
                    drugDto.setImported(1);
                }
                if (drugDto.getIsSuccess()==2){
                    drugDto.setStandardCode(staCode+"");
                    staCode+=1;
                    insertDrug.add(drugDto);
                }else if (drugDto.getIsSuccess()==0){
                    notInsertDrug.add(drugDto);
                }


                // 药品厂商表
                if ((drugDto.getIsSuccess()==1 || drugDto.getIsSuccess()==2) && tmpFactoryDto.getIsHave()!=-1){
                    if (StringUtils.isNotBlank(item.getComname()) && !item.getFactName().equals("----") && !item.getFactName().equals("--")){
                        TmpDictionaryDto tmpDictionaryDto = new TmpDictionaryDto();
                        tmpDictionaryDto.setId(IdWorker.getInstance().getId());
                        tmpDictionaryDto.setSpecid(drugDto.getId()+"");
                        tmpDictionaryDto.setComname(item.getComname());

                        tmpDictionaryDto.setAppCode(item.getAppCode());
                        tmpDictionaryDto.setFactId(tmpFactoryDto.getId());


                        String drugCodeSpda = item.getDrugcodeSpda();
                        if (drugCodeSpda.equals("86904495000166；86904495000173")){
                            System.out.println("----");
                        }
                        int indexof =  drugCodeSpda.indexOf("；");
                        if (indexof>0){
                            drugCodeSpda = drugCodeSpda.substring(0,indexof);
                        }
                        int indexof1 =  drugCodeSpda.indexOf(";");
                        if (indexof1>0){
                            drugCodeSpda = drugCodeSpda.substring(0,indexof1);
                        }
                        tmpDictionaryDto.setDrugcodeSpda(drugCodeSpda);
                        insertDict.add(tmpDictionaryDto);
                    }
                }
                System.out.println("执行第 "+ i + " 行完成");
            }

            String sqlPath = "C:/Users/Yan/Desktop/temporary/yjjSql/";
            //成功的企业sql
            String factSql = sqlPath + page.getPageNo()+"factSuccess.txt";
            writeFact(insertFact,factSql);

            //成功的药品规格sql
            String drugSql = sqlPath + page.getPageNo()+"drugSuccess.txt";
            writeSpecs(insertDrug,drugSql);

            //错误信息先写入json，在用网页的工具将json转为excel
            String drugErrorJson = sqlPath + page.getPageNo()+ "drugError.json";
            writeSpecsError(notInsertDrug,drugErrorJson);

            //成功的药品厂家sql
            String dictSql = sqlPath + page.getPageNo()+ "dictSuccess.txt";
            writeDict(insertDict,dictSql);

            currSize = list.size();
        }
        return "ok";
    }

    public TmpFactoryDto handleFactory(String factName){
        TmpFactoryDto tmpFactory = new TmpFactoryDto();
        Long haveFact = isHaveFact(factName);
        tmpFactory.setFactName(factName);
        if (haveFact!= null){
            tmpFactory.setId(haveFact);
            tmpFactory.setIsHave(0L);
        }else{
            long newId = IdWorker.getInstance().getId();
            tmpFactory.setId(newId);
            //新增
            tmpFactory.setIsHave(1L);
            factMap.put(factName,newId);
        }
        return tmpFactory;
    }

    public UscDrgSpecsDto handleSpecs(TmpYjjData item){
     /*   List<UscDrgSpecsDto> insertDrug = new ArrayList<UscDrgSpecsDto>();
        List<UscDrgSpecsDto> notInsert = new ArrayList<UscDrgSpecsDto>();*/

        UscDrgSpecsDto drug = new UscDrgSpecsDto();
        //标记 0-没有错误 1-有错误
        int flag = 0;
        String readError = "";
        //药品名称
        String data = item.getChemicalName();
        if (StringUtils.isNotBlank(data)){
            data.trim();
            if (data.equals("氧") || data.equals("氧(液态)") || data.equals("氧(气态)")){
                item.setDrugDosformCode("吸入剂");
            }
            if (data.equals("----")){
                flag=1;
                readError = "药品名称 "+ data +" 不合法";
            }else{
                drug.setChemicalName(data);
            }
        }
        //药品英文名称
        data = item.getEngName();
        if (StringUtils.isNotBlank(data)){
            drug.setEngName(data.trim());
        }
        //剂型
        data = item.getDrugDosformCode();
        if (StringUtils.isNotBlank(data)){
            data.trim();
            if (data.equals("注射剂") || data.equals("小容量注射剂") || data.equals("注射剂用稀释剂") || data.equals("脂质体注射剂")
                    || data.equals("注射剂，Injection") || data.equals("注射剂,Injection") || data.equals("注射剂(多剂量笔)") || data.equals("注射剂,Powder for Injection")
                    || data.equals("注射剂用稀释剂") || data.equals("注射剂(小容量注射剂)") || data.equals("注射剂(注射液)") || data.equals("注射用氢氧化铝混悬液")
                    || data.equals("注射剂(大容量注射剂)") || data.equals("大容量注射剂")){
                data = "注射液";
            }else if (data.equals("宫内节育系统") || data.equals("植入剂") || data.equals("阴道环") || data.equals("点刺液")
                    || data.equals("液体") || data.equals("菌粉") || data.equals("制剂中间体") || data.equals("液体制剂")
                    || data.equals("液体剂") || data.equals("其他") || data.equals("阴道灌注液")){
                data = "其它";
            }else if (data.equals("鼻用制剂") || data.equals("鼻用喷雾剂") || data.equals("滴鼻剂") || data.equals("鼻用制剂(滴鼻剂)")){
                data = "滴鼻液";
            }else if (data.equals("吸入气雾剂")){
                data = "气雾剂";
            }else if (data.equals("咀嚼胶")){
                data = "咀嚼片";
            }else if (data.equals("滴眼剂") || data.trim().equals("眼用制剂") || data.trim().equals("眼用制剂(滴眼剂)") || data.trim().equals("眼用制剂（滴眼剂）")){
                data = "滴眼液";
            }else if (data.trim().equals("口服混悬剂") || data.equals("混悬剂") || data.equals("吸入混悬剂") || data.equals("雾化吸入用混悬剂")){
                data = "混悬液";
            }else if (data.equals("体外诊断试剂") || data.equals("诊断试剂盒") || data.equals("体内诊断试剂")
                    || data.equals("体外诊断试剂盒") || data.equals("试剂盒")){
                data = "诊断试剂";
            }else if (data.equals("口服乳剂") || data.equals("乳剂") || data.equals("乳胶剂")){
                data = "乳膏剂";
            }else if (data.equals("外用溶液") || data.equals("外用药油") || data.equals("外用搽剂") || data.equals("外用凝胶剂")){
                data = "外用";
            }else if (data.equals("雾化溶液剂") || data.equals("外用溶液剂") || data.equals("吸入用溶液剂") || data.equals("口服溶液")
                    || data.equals("注射溶液") || data.equals("溶液剂（外用）") || data.equals("溶液剂(外用)")){
                data = "溶液剂";
            }else if (data.equals("贴膏剂") || data.equals("透皮贴剂")){
                data = "贴剂";
            }else if (data.equals("胶囊剂(肠溶)")){
                data = "肠溶胶囊";
            }else if (data.equals("吸入制剂") || data.equals("吸入粉雾剂") || data.equals("吸入麻醉剂")
                    || data.equals("吸入溶液剂") || data.equals("吸入用溶液剂")){
                data = "吸入剂";
            }else if (data.equals("散剂(口服)") || data.equals("散剂(内服)") || data.equals("散剂（口服）") || data.equals("散剂(外用)")){
                data = "散剂";
            }else if (data.equals("粉雾剂") || data.equals("菌粉")){
                data = "粉剂";
            }else if (data.equals("胶丸剂") || data.equals("胶剂") || data.equals("缓释胶囊")){
                data = "胶囊";
            }else if (data.equals("缓释胶囊剂") || data.equals("软胶囊剂") || data.equals("硬胶囊剂") || data.equals("胶囊剂,Capsule")
                    || data.equals("胶囊剂(软胶囊)") || data.equals("胶囊剂(硬胶囊)") || data.equals("胶囊剂(缓释)") || data.equals("胶囊剂（硬胶囊）")
                    || data.equals("剂囊剂") || data.equals("胶囊剂（肠溶）") || data.equals("胶囊剂(肠溶胶囊)") || data.equals("胶囊剂(肠溶片)")
                    || data.equals("胶囊剂（软胶囊）")){
                data = "胶囊剂";
            }else if (data.equals("丸剂(小蜜丸)") || data.equals("丸剂(大蜜丸)") || data.equals("小蜜丸") || data.equals("大蜜丸")
                    || data.equals("丸剂(浓缩丸)") || data.equals("丸剂（水丸）") || data.equals("丸剂(水蜜丸)") || data.equals("丸剂(浓缩水蜜丸)")
                    || data.equals("丸剂﹙大蜜丸﹚") || data.equals("(1)丸剂(浓缩蜜丸)(2)丸剂(大蜜丸)") || data.equals("丸剂（浓缩蜜丸）")
                    || data.equals("丸剂(浓缩水丸)") || data.equals("丸剂(浓缩蜜丸)") || data.equals("丸剂(大蜜丸)；丸剂(水蜜丸)") || data.equals("丸剂(水丸)")
                    || data.equals("丸剂(浓缩蜜丸)；丸剂") || data.equals("丸剂(浓缩蜜丸、小蜜丸)") || data.equals("丸剂(大蜜丸 )") || data.equals("丸剂(大蜜丸、小蜜丸)")
                    || data.equals("丸剂(糊丸)") || data.equals("丸剂﹙水丸﹚") || data.equals("丸剂（大蜜丸）") || data.equals("丸剂（浓缩丸）") || data.equals("丸剂（小蜜丸）")
                    || data.equals("丸剂（水蜜丸）") || data.equals("丸剂（浓缩丸）") || data.equals("丸剂(薄膜衣)") || data.equals("丸剂(糖衣)") || data.equals("丸剂(浓缩水蜜丸,浓缩水丸)")
                    || data.equals("丸剂(大蜜丸,小蜜丸)") || data.equals("丸剂(浓缩大蜜丸)") || data.equals("丸剂(包薄膜衣水丸)") || data.equals("丸剂（糊丸）") || data.equals("丸剂(浓缩水蜜丸或浓缩水丸)")
                    || data.equals("丸剂(薄膜衣水丸)") || data.equals("丸剂(包衣水丸)") || data.equals("丸剂(浓缩微丸)") || data.equals("丸剂(蜜丸)")
                    || data.equals("丸剂 (水蜜丸)") || data.equals("丸剂(滴丸剂)") ){
                data = "丸剂";
            }else if (data.equals("涂膜剂")){
                data = "涂剂";
            }else if (data.equals("片剂(糖衣)") || data.equals("咀嚼片剂") || data.equals("口腔崩解片剂") || data.equals("片剂,Tablet")
                    || data.equals("分散片剂") || data.equals("贴脐片剂") || data.equals("阴道泡腾片剂") || data.equals("控释片剂")
                    || data.equals("片剂，Tablet") || data.equals("片剂(薄膜衣)") || data.equals("片剂（糖衣片）") || data.equals("片剂(薄膜衣,糖衣片)")
                    || data.equals("片剂（含片）") || data.equals("片剂(含片)") || data.equals("片剂(分散片)") || data.equals("片剂(分散)")
                    || data.equals("片剂(薄膜衣片、糖衣片)") || data.equals("片剂(薄膜衣片)") || data.equals("片剂(素片、薄膜衣片)") || data.equals("片剂(素片)")
                    || data.equals("片剂(薄膜衣片、素片)") || data.equals("片剂(贴片)") || data.equals("片剂(糖衣或薄膜衣)") || data.equals("片剂(咀嚼)")
                    || data.equals("片剂(薄膜衣片,浸膏片)") || data.equals("片剂（薄膜衣）") || data.equals("片剂(糖衣;薄膜衣)") || data.equals("片剂(素片、薄膜衣)")
                    || data.equals("片剂(浸膏片、薄膜衣片)") || data.equals("片剂(糖衣片、薄膜衣片)") || data.equals("片剂(泡腾)") || data.equals("片剂（薄膜衣）")
                    || data.equals("片剂(浸膏片)") || data.equals("片剂(糖衣、薄膜衣)") || data.equals("片剂(肠溶片)") || data.equals("片剂(素片,薄膜衣片)")
                    || data.equals("片剂（口含）") || data.equals("片剂（薄膜衣片）") || data.equals("片剂(肠溶)") || data.equals("片剂(糖衣片、薄膜衣)") || data.equals("片剂(薄膜衣片,糖衣片)")
                    || data.equals("片剂(咀嚼片)") || data.equals("片剂（糖衣、薄膜衣）") || data.equals("片剂(糖衣,薄膜衣)") || data.equals("薄膜衣片") || data.equals("片剂（薄衣片）")
                    || data.equals("片剂（糖衣片、薄膜衣片）") || data.equals("片剂（糖衣）") || data.equals("片剂(糖衣片)") || data.equals("片剂(缓释片)") || data.equals("片剂（泡腾片）")
                    || data.equals("片剂(缓释片)") || data.equals("片剂(口含片)") || data.equals("片剂（素片）") || data.equals("片剂 (薄膜衣)") || data.equals("片剂(薄膜衣片;糖衣片)")
                    || data.equals("片剂（缓释）") || data.equals("含片") || data.equals("片剂(素片,糖衣片)") || data.equals("片剂(口含)") || data.equals("片剂(糖衣片;薄膜衣片)") || data.equals("片剂(糖衣片,薄膜衣片)")
                    || data.equals("片剂(缓释) ")){
                data = "片剂";
            }else if (data.equals("鼻喷雾剂")){
                data = "喷雾剂";
            }else if (data.equals("滴丸剂") || data.equals("口服滴剂")){
                data = "滴剂";
            }else if (data.equals("洗剂") || data.equals("冲洗剂")){
                data = "冲洗液";
            }else if (data.equals("冻干粉针剂") || data.equals("无菌分装粉针剂")){
                data = "粉针剂";
            }else if (data.equals("耳用制剂")){
                data = "滴耳剂";
            }else if (data.equals("缓释制剂") || data.equals("缓释片剂") || data.equals("缓释小丸")){
                data = "缓释片";
            }else if (data.equals("含漱剂")){
                data = "含漱液";
            }else if (data.equals("泡腾颗粒剂") || data.equals("颗粒剂(无糖型)") || data.equals("颗粒剂(含糖)") || data.equals("颗粒剂(混悬颗粒)")
                    || data.equals("颗粒剂(无糖)") || data.equals("颗粒剂(块状颗粒)")){
                data = "颗粒剂";
            }else if (data.equals("水丸")){
                data = "水剂";
            }else if (data.equals("流浸膏剂") || data.equals("橡胶膏剂") || data.equals("贴膏剂(橡胶膏剂)") || data.equals("贴膏剂(橡胶贴膏)")
                    || data.equals("贴膏剂(凝胶膏剂)")){
                data = "膏剂";
            }else if (data.equals("无菌冻干粉") || data.equals("冻干粉末") || data.equals("冻干粉剂")
                    || data.equals("冻干")){
                data = "冻干粉";
            }else if (data.equals("煎膏剂(膏滋)")){
                data = "煎膏剂";
            }else if (data.equals("搽剂(油剂)")){
                data = "油剂";
            }else if (data.equals("原料药,Bulk") || data.equals("原料药(供注射用)")){
                data = "原料药";
            }else if (data.equals("合剂(口服液)") || data.equals("合剂(有糖型,无糖型)")){
                data = "合剂";
            }else if (data.equals("茶剂（袋装茶剂）")){
                data = "茶剂";
            }else if (data.equals("熏蒸剂")){
                data = "熏剂";
            }else if (data.equals("剂型(泡腾片)") || data.equals("片剂(泡腾片)")){
                data = "泡腾片";
            }else if (data.equals("胶囊(软胶囊)")){
                data = "软胶囊";
            }

            String jxCode = isHave(data, dosform);
            if (StringUtils.isNotBlank(jxCode)){
                drug.setDrugDosformCode(jxCode);
            }else {
                readError += "--剂型  " + item.getDrugDosformCode() + "  字典中无匹配项";
                flag = 1;
            }
        }else{
            readError += "--剂型 为空";
            flag = 1;
        }
        //类型
        data = item.getDrugType();
        if (StringUtils.isNotBlank(data)){
            data.trim();
            if (data.trim().equals("中药")){
                data = "中草药";
            }else if (data.trim().equals("化学药品")){
                data = "西药";
            }else if (data.trim().equals("生物制品")){
                data = "疫苗";
            }
            String lxCode = isHave(data, drugType);
            if (StringUtils.isNotBlank(lxCode)){
                drug.setDrugType(lxCode);
            }else {
                readError += "--药品类型  " + item.getDrugType() + "  字典中无匹配项";
                flag = 1;
            }
        }else{
            readError += "--药品类型 为空";
            flag = 1;
        }
        //药品本位码
        data = item.getDrugcodeSpda();
        if (StringUtils.isBlank(data)){
            readError += "--药品本位码 为空";
            flag = 1;
        }else{
            drug.setDrugCodeSpda(data.trim());
        }

        //规格
        drug.setSpecDesc(item.getSpecDesc());
        //版本id
        drug.setVersionId(item.getVersionId());
        if (flag==1){
            drug.setReadError(readError);
            drug.setIsSuccess(0L);
        }else{
            Long drugId = jkypMap.get(drug.getChemicalName() + drug.getDrugDosformCode() + drug.getDrugType());
            if (drugId != null){
                drug.setId(drugId);
                drug.setIsSuccess(1L);
            }else{
                drug.setId(IdWorker.getInstance().getId());
                jkypMap.put(drug.getChemicalName()+drug.getDrugDosformCode()+drug.getDrugType(),drug.getId());
                drug.setIsSuccess(2L);
            }
        }

        return drug;
    }
}
