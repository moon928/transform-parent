package cn.trasen.mcpc.transform.service;

import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 中文转拼音与五笔码测试表服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TTestService {
    /**
     * 增加中文转拼音与五笔码测试表
     *
     * @author Yan_zt
     * @date: 2020-06-22 10:47:57
     * @param operContext 操作上下文
     * @param tTest 中文转拼音与五笔码测试表
     */
    void add(OperContext operContext, TTest tTest);

    /**
     * 根据ID修改中文转拼音与五笔码测试表
     *
     * @author Yan_zt
     * @date: 2020-06-22 10:47:57
     * @param operContext 操作上下文
     * @param tTest 中文转拼音与五笔码测试表
     */
    void modifyById(OperContext operContext, TTest tTest);

    /**
     * 根据ID删除中文转拼音与五笔码测试表
     *
     * @author Yan_zt
     * @date: 2020-06-22 10:47:57
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取中文转拼音与五笔码测试表
     *
     * @author Yan_zt
     * @date: 2020-06-22 10:47:57
     * @param id 主键
     * @return
     */
    TTest getById(Long id);

    /**
     * 分页查询
     * @param query
     * @param page
     * @return
     */
    DataSet<TTest> page(String query, Page page);

    /**
     * 中文转五笔和拼音
     * @param tableName
     * @param columnName 列明
     * @return
     */
    int chineseToPyAndWb(String tableName,String columnName);
}