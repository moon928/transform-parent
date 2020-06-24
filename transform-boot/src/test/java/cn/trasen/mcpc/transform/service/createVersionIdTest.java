package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.framework.util.IdWorker;
import cn.trasen.mcpc.transform.util.WriteFile;

/**
 * @program: project-parent
 * @description: 添加字典中国家药监局版本id
 * @author: yan_zt
 * @create: 2020-06-23 20:20
 */
public class createVersionIdTest {

    public static void main(String[] args) {
//        long id = IdWorker.getInstance().getId();
//        String name = "国家药监局版本";
//        String resourceType = "1";
//        String channelType = "0";
//        String status = "1";
//        String deleteFlag = "0";
//        String sql = "";
//        sql = String.format("INSERT INTO usc_dict_version (ID, NAME, RESOURCE_TYPE,CHANNEL_TYPE,STATUS,DELETE_FLAG)" +
//                " VALUES ('%s', '%s', '%s','%s','%s','%s');\n",
//                id,name,resourceType,channelType,status,deleteFlag);
////        WriteFile.writeToFile("C:\\Users\\Yan\\Desktop\\temporary\\versionId.txt",sql);
//        System.out.println(sql);

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

}
