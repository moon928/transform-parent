package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.framework.util.IdWorker;
import cn.trasen.mcpc.transform.model.UscDictDetail;
import cn.trasen.mcpc.transform.util.WriteFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: project-parent
 * @description: 添加字典中国家药监局版本id
 * @author: yan_zt
 * @create: 2020-06-23 20:20
 */
public class createVersionIdTest {

    public static void main(String[] args) {

//        handleDosform();
        System.out.println(IdWorker.getInstance().getId());


    }

    /**
     * 处理药品版本
     */
    public void handleVersionId(){
        long id = IdWorker.getInstance().getId();
        String name = "国家药监局版本";
        String resourceType = "1";
        String channelType = "0";
        String status = "1";
        String deleteFlag = "0";
        String sql = "";
        sql = String.format("INSERT INTO usc_dict_version (ID, NAME, RESOURCE_TYPE,CHANNEL_TYPE,STATUS,DELETE_FLAG)" +
                " VALUES ('%s', '%s', '%s','%s','%s','%s');\n",
                id,name,resourceType,channelType,status,deleteFlag);
        WriteFile.writeToFile("C:\\Users\\Yan\\Desktop\\temporary\\versionId.txt",sql);
        System.out.println(sql);
    }

    /**
     * 处理药品类型
     */
    public void handleDictDetail(){
        long id = IdWorker.getInstance().getId();
        String code = "4";
        String name = "疫苗";
        String catalogCode = "drug_type";
        String catalogName = "药品类型";
        String sql = "";
        sql = String.format("INSERT INTO usc_dict_detail (ID, CODE, NAME,CATALOG_CODE,CATALOG_NAME)" +
                        " VALUES ('%s', '%s', '%s','%s','%s');\n",
                id,code,name,catalogCode,catalogName);
        WriteFile.writeToFile("C:\\Users\\Yan\\Desktop\\temporary\\dictDetail.txt",sql);
        System.out.println(sql);
    }

    /**
     * 处理剂型
     */
    public static void handleDosform(){

        List<UscDictDetail>  dictDetailList = new ArrayList<UscDictDetail>();
        UscDictDetail detail1 = new UscDictDetail();
        detail1.setId(IdWorker.getInstance().getId());
        detail1.setName("诊断试剂");
        detail1.setCatalogCode("DOSFORM");
        detail1.setCatalogName("药品剂型");

        UscDictDetail detail2 = new UscDictDetail();
        detail2.setId(IdWorker.getInstance().getId());
        detail2.setName("油剂");
        detail2.setCatalogCode("DOSFORM");
        detail2.setCatalogName("药品剂型");

        UscDictDetail detail3 = new UscDictDetail();
        detail3.setId(IdWorker.getInstance().getId());
        detail3.setName("灌肠剂");
        detail3.setCatalogCode("DOSFORM");
        detail3.setCatalogName("药品剂型");

        UscDictDetail detail4 = new UscDictDetail();
        detail4.setId(IdWorker.getInstance().getId());
        detail4.setName("原料药");
        detail4.setCatalogCode("DOSFORM");
        detail4.setCatalogName("药品剂型");

        UscDictDetail detail5 = new UscDictDetail();
        detail5.setId(IdWorker.getInstance().getId());
        detail5.setName("膏药");
        detail5.setCatalogCode("DOSFORM");
        detail5.setCatalogName("药品剂型");

        UscDictDetail detail6 = new UscDictDetail();
        detail6.setId(IdWorker.getInstance().getId());
        detail6.setName("煎膏剂");
        detail6.setCatalogCode("DOSFORM");
        detail6.setCatalogName("药品剂型");

        UscDictDetail detail7 = new UscDictDetail();
        detail7.setId(IdWorker.getInstance().getId());
        detail7.setName("放免药盒");
        detail7.setCatalogCode("DOSFORM");
        detail7.setCatalogName("药品剂型");

        UscDictDetail detail8 = new UscDictDetail();
        detail8.setId(IdWorker.getInstance().getId());
        detail8.setName("茶剂");
        detail8.setCatalogCode("DOSFORM");
        detail8.setCatalogName("药品剂型");

        UscDictDetail detail9 = new UscDictDetail();
        detail9.setId(IdWorker.getInstance().getId());
        detail9.setName("露剂");
        detail9.setCatalogCode("DOSFORM");
        detail9.setCatalogName("药品剂型");

        UscDictDetail detail10 = new UscDictDetail();
        detail10.setId(IdWorker.getInstance().getId());
        detail10.setName("曲剂");
        detail10.setCatalogCode("DOSFORM");
        detail10.setCatalogName("药品剂型");

        UscDictDetail detail11 = new UscDictDetail();
        detail11.setId(IdWorker.getInstance().getId());
        detail11.setName("硬膏剂");
        detail11.setCatalogCode("DOSFORM");
        detail11.setCatalogName("药品剂型");

        UscDictDetail detail12 = new UscDictDetail();
        detail12.setId(IdWorker.getInstance().getId());
        detail12.setName("露剂");
        detail12.setCatalogCode("DOSFORM");
        detail12.setCatalogName("药品剂型");

        UscDictDetail detail13 = new UscDictDetail();
        detail13.setId(IdWorker.getInstance().getId());
        detail13.setName("醑剂");
        detail13.setCatalogCode("DOSFORM");
        detail13.setCatalogName("药品剂型");

        UscDictDetail detail14 = new UscDictDetail();
        detail14.setId(IdWorker.getInstance().getId());
        detail14.setName("灸剂");
        detail14.setCatalogCode("DOSFORM");
        detail14.setCatalogName("药品剂型");

        UscDictDetail detail15 = new UscDictDetail();
        detail15.setId(IdWorker.getInstance().getId());
        detail15.setName("湿敷剂");
        detail15.setCatalogCode("DOSFORM");
        detail15.setCatalogName("药品剂型");

        UscDictDetail detail16 = new UscDictDetail();
        detail16.setId(IdWorker.getInstance().getId());
        detail16.setName("火棉胶剂");
        detail16.setCatalogCode("DOSFORM");
        detail16.setCatalogName("药品剂型");

        UscDictDetail detail17 = new UscDictDetail();
        detail17.setId(IdWorker.getInstance().getId());
        detail17.setName("熨剂");
        detail17.setCatalogCode("DOSFORM");
        detail17.setCatalogName("药品剂型");

        UscDictDetail detail18 = new UscDictDetail();
        detail18.setId(IdWorker.getInstance().getId());
        detail18.setName("膜剂");
        detail18.setCatalogCode("DOSFORM");
        detail18.setCatalogName("药品剂型");

        UscDictDetail detail19 = new UscDictDetail();
        detail19.setId(IdWorker.getInstance().getId());
        detail19.setName("锭剂");
        detail19.setCatalogCode("DOSFORM");
        detail19.setCatalogName("药品剂型");

        UscDictDetail detail20 = new UscDictDetail();
        detail20.setId(IdWorker.getInstance().getId());
        detail20.setName("含漱液");
        detail20.setCatalogCode("DOSFORM");
        detail20.setCatalogName("药品剂型");

        UscDictDetail detail21 = new UscDictDetail();
        detail21.setId(IdWorker.getInstance().getId());
        detail21.setName("热熨剂");
        detail21.setCatalogCode("DOSFORM");
        detail21.setCatalogName("药品剂型");

        UscDictDetail detail22 = new UscDictDetail();
        detail22.setId(IdWorker.getInstance().getId());
        detail22.setName("灸熨剂");
        detail22.setCatalogCode("DOSFORM");
        detail22.setCatalogName("药品剂型");

        dictDetailList.add(detail1);
        dictDetailList.add(detail2);
        dictDetailList.add(detail3);
        dictDetailList.add(detail4);
        dictDetailList.add(detail5);
        dictDetailList.add(detail6);
        dictDetailList.add(detail7);
        dictDetailList.add(detail8);
        dictDetailList.add(detail9);
        dictDetailList.add(detail10);
        dictDetailList.add(detail11);
        dictDetailList.add(detail12);
        dictDetailList.add(detail13);
        dictDetailList.add(detail14);
        dictDetailList.add(detail15);
        dictDetailList.add(detail16);
        dictDetailList.add(detail17);
        dictDetailList.add(detail18);
        dictDetailList.add(detail19);
        dictDetailList.add(detail20);
        dictDetailList.add(detail21);
        dictDetailList.add(detail22);

        String sql = "";
        int code = 56;
        for (UscDictDetail item: dictDetailList){
            sql += String.format("INSERT INTO usc_dict_detail (ID, CODE, NAME,CATALOG_CODE,CATALOG_NAME)" +
                            " VALUES ('%s', '%s', '%s','%s','%s');\n",
                    item.getId(),code,item.getName(),item.getCatalogCode(),item.getCatalogName());
            code+=1;
        }
        WriteFile.writeToFile("C:\\Users\\Yan\\Desktop\\temporary\\dictDetail-DOSFORM.txt",sql);
        System.out.println(sql);
    }
}
