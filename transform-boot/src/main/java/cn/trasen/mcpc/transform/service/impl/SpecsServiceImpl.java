package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.util.IdWorker;
import cn.trasen.mcpc.transform.dto.UscDrgSpecsDto;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.model.UscDictDetail;
import cn.trasen.mcpc.transform.model.UscDrgSpecs;
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

    private Map<String,String> detailMap = new HashMap<>();

    /**
     * 进口药品临时map
     */
    private Map<String,Long> jkypMap = new HashMap<String,Long>();
    @Autowired
    private UscDictDetailService uscDictDetailService;

    //剂型 字典
    String dosform = "DOSFORM";
    //药品类型字典
    String drugType = "drug_type";
    @Override
    public Mapper<UscDrgSpecs> getMapper() {
        return null;
    }

    void initializeDetail(){
        List<UscDictDetail> list = uscDictDetailService.list(null);
        for (UscDictDetail uscDictDetail : list){

            detailMap.put(uscDictDetail.getName()+uscDictDetail.getCatalogCode(),uscDictDetail.getCode());
        }
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
            if (jkypMap.get(item.getChemicalName()+item.getDrugDosformCode()+item.getDrugType()) != null){
                continue;
            }else{
                item.setId(IdWorker.getInstance().getId());
                insertDrug.add(item);
                jkypMap.put(item.getChemicalName()+item.getDrugDosformCode()+item.getDrugType(),item.getId());
            }
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
            sql += String.format("INSERT INTO usc_drg_specs (ID,STANDARD_CODE,CHEMICAL_NAME,DRUG_DOSFORM_CODE," +
                            "DRUG_TYPE,SPEC_DESC,VERSION_ID,IMPORTED)" +
                            "VALUES ('%s', '%s', '%s','%s','%s','%s','%s','%s');\n",
                    item.getId(),item.getStandardCode(),item.getChemicalName(),item.getDrugDosformCode(),item.getDrugType(),
                    item.getSpecDesc(),item.getVersionId(),item.getImported());
        }

        WriteFile.writeToFile(filePath,sql);
    }

    public void writeSpecsError(List<UscDrgSpecsDto> notInsertDrug,String filePath){

        String json = "[";
        for (UscDrgSpecsDto item :notInsertDrug){
            json += JSON.toJSONString(item) + ",\n";
        }
        WriteFile.writeToFile(filePath,json);
    }

    @Override
    public String jkypSpecs() {
        return null;
    }
}
