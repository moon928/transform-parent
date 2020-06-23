package cn.trasen.mcpc.transform.dao;

import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.model.ToPyAndWbDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 中文转拼音与五笔码测试表数据库操作.
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
public interface TTestMapper extends Mapper<TTest> {
    /**
     * 分页查询
     * @param query
     * @param page
     * @return
     */
    List<TTest> page(@Param("query") String query, Page page);


    /**
     * 通过表名 分页查询
     * @param query
     * @param page
     * @return
     */
    List<ToPyAndWbDto> pageNameByTableName(@Param("tableName") String query,@Param("columnName") String columnName, Page page);

    /**
     * 批量修改相关表的 拼音编码与五笔编码
     *
     * @param tableName
     * @param list
     * @return
     */
    int modifyBatch(@Param("tableName") String tableName,@Param("list") List<ToPyAndWbDto> list);
}