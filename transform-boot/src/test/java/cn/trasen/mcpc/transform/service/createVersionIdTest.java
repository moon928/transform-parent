package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.framework.util.IdWorker;

/**
 * @program: project-parent
 * @description: 添加字典中国家药监局版本id
 * @author: yan_zt
 * @create: 2020-06-23 20:20
 */
public class createVersionIdTest {

    public static void main(String[] args) {
        long id = IdWorker.getInstance().getId();
        String name = "国家药监局版本";
        String resourceType = "1";
        String channelType = "0";
        String status = "1";
        String deleteFlag = "0";
        String sql = String.format("INSERT INTO usc_dict_version (ID, NAME, RESOURCE_TYPE,CHANNEL_TYPE,STATUS,DELETE_FLAG)" +
                " VALUES (%s, %s, '%s','%s','%s','%s'));\n",
                id,name,resourceType,channelType,status,deleteFlag);

        System.out.println(sql);
    }
}
