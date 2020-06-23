package cn.trasen.mcpc.transform.service;

/**
 * @program: project-parent
 * @description: 药品Service
 * @author: yan_zt
 * @create: 2020-06-23 16:30
 */
public interface SpecsService {
    String test();

    /**
     * 进口药品转sql
     * @param excelPath excel地址
     * @param sqlPath sql存入地址
     * @return
     */
    String jkypToSql(String excelPath, String sqlPath);
}
