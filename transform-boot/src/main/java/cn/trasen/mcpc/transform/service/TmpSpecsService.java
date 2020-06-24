package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.transform.model.TmpSpecs;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 临时的 药品规格表服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TmpSpecsService {
    /**
     * 增加临时的 药品规格表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param operContext 操作上下文
     * @param tmpSpecs 临时的 药品规格表
     */
    void add(OperContext operContext, TmpSpecs tmpSpecs);

    /**
     * 根据ID修改临时的 药品规格表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param operContext 操作上下文
     * @param tmpSpecs 临时的 药品规格表
     */
    void modifyById(OperContext operContext, TmpSpecs tmpSpecs);

    /**
     * 根据ID删除临时的 药品规格表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取临时的 药品规格表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:45
     * @param id 主键
     * @return
     */
    TmpSpecs getById(Long id);
}