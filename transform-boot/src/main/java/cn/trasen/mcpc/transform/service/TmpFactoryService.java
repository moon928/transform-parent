package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.transform.model.TmpFactory;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 临时的 药品生产厂家服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TmpFactoryService {
    /**
     * 增加临时的 药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param operContext 操作上下文
     * @param tmpFactory 临时的 药品生产厂家
     */
    void add(OperContext operContext, TmpFactory tmpFactory);

    /**
     * 根据ID修改临时的 药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param operContext 操作上下文
     * @param tmpFactory 临时的 药品生产厂家
     */
    void modifyById(OperContext operContext, TmpFactory tmpFactory);

    /**
     * 根据ID删除临时的 药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取临时的 药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param id 主键
     * @return
     */
    TmpFactory getById(Long id);
}