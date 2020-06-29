package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.transform.model.UscStatItem;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 大项目分类联动关系表服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-29 18:32:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface UscStatItemService {
    /**
     * 增加大项目分类联动关系表
     * 
     * @author Yan_zt
     * @date: 2020-06-29 18:32:44
     * @param operContext 操作上下文
     * @param uscStatItem 大项目分类联动关系表
     */
    void add(OperContext operContext, UscStatItem uscStatItem);

    /**
     * 根据ID修改大项目分类联动关系表
     * 
     * @author Yan_zt
     * @date: 2020-06-29 18:32:44
     * @param operContext 操作上下文
     * @param uscStatItem 大项目分类联动关系表
     */
    void modifyById(OperContext operContext, UscStatItem uscStatItem);

    /**
     * 根据ID删除大项目分类联动关系表
     * 
     * @author Yan_zt
     * @date: 2020-06-29 18:32:44
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取大项目分类联动关系表
     * 
     * @author Yan_zt
     * @date: 2020-06-29 18:32:44
     * @param id 主键
     * @return
     */
    UscStatItem getById(Long id);
}