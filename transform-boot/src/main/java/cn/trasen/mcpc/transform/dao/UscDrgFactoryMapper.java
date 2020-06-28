package cn.trasen.mcpc.transform.dao;

import cn.trasen.mcpc.transform.model.UscDrgFactory;
import tk.mybatis.mapper.common.Mapper;

/**
 * 药品生产厂家数据库操作.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 17:44:15
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface UscDrgFactoryMapper extends Mapper<UscDrgFactory> {
    /**
     * 通过名称查询药品企业
     * @param factName
     * @return
     */
    UscDrgFactory getByFactName(String factName);
}