package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.transform.model.TmpDictionary;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 临时的 药品厂家表服务.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TmpDictionaryService {
    /**
     * 增加临时的 药品厂家表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:44
     * @param operContext 操作上下文
     * @param tmpDictionary 临时的 药品厂家表
     */
    void add(OperContext operContext, TmpDictionary tmpDictionary);

    /**
     * 根据ID修改临时的 药品厂家表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:44
     * @param operContext 操作上下文
     * @param tmpDictionary 临时的 药品厂家表
     */
    void modifyById(OperContext operContext, TmpDictionary tmpDictionary);

    /**
     * 根据ID删除临时的 药品厂家表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:44
     * @param operContext 操作上下文
     * @param id 主键
     */
    void removeById(OperContext operContext, Long id);

    /**
     * 根据ID获取临时的 药品厂家表
     * 
     * @author Yan_zt
     * @date: 2020-06-24 16:45:44
     * @param id 主键
     * @return
     */
    TmpDictionary getById(Long id);
}