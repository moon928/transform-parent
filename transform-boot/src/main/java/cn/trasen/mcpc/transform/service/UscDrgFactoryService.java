package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.transform.model.UscDrgFactory;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 药品生产厂家服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 17:44:15
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface UscDrgFactoryService {
    /**
     * 增加药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 17:44:15
     * @param operContext 操作上下文
     * @param uscDrgFactory 药品生产厂家
     */
    void add(OperContext operContext, UscDrgFactory uscDrgFactory);

    /**
     * 根据ID修改药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 17:44:15
     * @param operContext 操作上下文
     * @param uscDrgFactory 药品生产厂家
     */
    void modifyById(OperContext operContext, UscDrgFactory uscDrgFactory);

    /**
     * 根据ID删除药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 17:44:15
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取药品生产厂家
     * 
     * @author Yan_zt
     * @date: 2020-06-24 17:44:15
     * @param id 主键
     * @return
     */
    UscDrgFactory getById(Long id);
}