package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.transform.model.ToPyAndWbDto;
import cn.trasen.mcpc.transform.util.PingYinToWubi;
import cn.trasen.mcpc.transform.util.PingYinUtils;
import cn.trasen.mcpc.transform.util.WuBiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.TTestMapper;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.service.TTestService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 中文转拼音与五笔码测试表服务实现.
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class TTestServiceImpl extends BaseServiceImpl<TTest> implements TTestService {

    @Autowired
    private TTestMapper tTestMapper;

    public static final int PAGE_SIZE = 5000;

    @Override
    public Mapper<TTest> getMapper() {
        return tTestMapper;
    }

    @Override
    public void add(OperContext operContext, TTest tTest) {
        super.insertSelective(operContext, tTest);
    }

    @Override
    public void modifyById(OperContext operContext, TTest tTest) {
        super.updateByPrimaryKeySelective(operContext, tTest);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public TTest getById(Long id) {
        TTest tTest = super.selectByPrimaryKey(id);
        return tTest;
    }

    @Override
    public DataSet<TTest> page(String query, Page page) {
        List<TTest> list = tTestMapper.page(query, page);
        return new DataSet(page.getPageNo(), page.getPageSize(), page.getTotalPages(), page.getTotalCount(), list);
    }

    @Override
    public int chineseToPyAndWb(String tableName, String columnName) {
        int totalCount = 0;
        int currSize = PAGE_SIZE;
        int pageIndex = 1;
        Page page = new Page();
        page.setPageSize(PAGE_SIZE);
        //分页查找
        while (currSize == PAGE_SIZE) {
            page.setPageNo(pageIndex++);
            List<ToPyAndWbDto> list = tTestMapper.pageNameByTableName(tableName,columnName, page);
            currSize = list.size();
            totalCount += toPyAndWb(tableName, list);
        }
        return totalCount;
    }

    public int toPyAndWb(String tableName, List<ToPyAndWbDto> list){
        int i = 0;
        for (ToPyAndWbDto item: list){
//            item.setPyCode(PingYinUtils.getFullSpell(item.getName()));
//            item.setWbCode(WuBiUtils.getWuBiCode(item.getName()));
            item.setPyCode(PingYinUtils.getAllPinYinHeadChar(item.getName()));
            item.setWbCode(PingYinToWubi.getWBCode(item.getName()));
            System.out.println(i++ +"  "+item.getName()+"  " + item.getPyCode() + " " + item.getWbCode());
        }
        tTestMapper.modifyBatch(tableName, list);
        return i;
    }
}