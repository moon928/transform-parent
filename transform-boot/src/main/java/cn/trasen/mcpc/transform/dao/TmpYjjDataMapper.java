package cn.trasen.mcpc.transform.dao;

import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.transform.model.TmpYjjData;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 药监局数据临时表数据库操作.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 13:23:59
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TmpYjjDataMapper extends Mapper<TmpYjjData> {

    /**
     * 批量插入药监局数据
     * @param list
     * @return
     */
    int addBatch(List<TmpYjjData> list);

    /**
     * 分页查询临时表数据
     * @param page
     * @param type 0-国产 1-进口
     * @return
     */
    List<TmpYjjData> page(Page page,@Param("type") String type);
}