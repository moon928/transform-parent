package cn.trasen.mcpc.transform.service;

import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.transform.model.TmpYjjData;
import cn.trasen.mcpc.framework.base.OperContext;

import java.util.List;

/**
 * 药监局数据临时表服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 13:23:59
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TmpYjjDataService {
    /**
     * 增加药监局数据临时表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 13:23:59
     * @param operContext 操作上下文
     * @param tmpYjjData 药监局数据临时表
     */
    void add(OperContext operContext, TmpYjjData tmpYjjData);

    /**
     * 根据ID修改药监局数据临时表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 13:23:59
     * @param operContext 操作上下文
     * @param tmpYjjData 药监局数据临时表
     */
    void modifyById(OperContext operContext, TmpYjjData tmpYjjData);

    /**
     * 根据ID删除药监局数据临时表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 13:23:59
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取药监局数据临时表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 13:23:59
     * @param id 主键
     * @return
     */
    TmpYjjData getById(Long id);

    /**
     * 批量插入药监局数据
     * @param excelPath
     * @return
     */
    int addBatchYzt(String excelPath);

    /**
     * 分页查询临时表数据
     * @param page
     * @param type 0-国产 1-进口
     * @return
     */
    List<TmpYjjData> page(Page page,String type);
}