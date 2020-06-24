package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.mcpc.transform.util.ReadExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.TmpYjjDataMapper;
import cn.trasen.mcpc.transform.model.TmpYjjData;
import cn.trasen.mcpc.transform.service.TmpYjjDataService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 药监局数据临时表服务实现.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 13:23:59
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class TmpYjjDataServiceImpl extends BaseServiceImpl<TmpYjjData> implements TmpYjjDataService {   

    @Autowired
    private TmpYjjDataMapper tmpYjjDataMapper;

    @Override
    public Mapper<TmpYjjData> getMapper() {
        return tmpYjjDataMapper;
    }
    
    @Override
    public void add(OperContext operContext, TmpYjjData tmpYjjData) {
        super.insertSelective(operContext, tmpYjjData);
    }

    @Override
    public void modifyById(OperContext operContext, TmpYjjData tmpYjjData) {
        super.updateByPrimaryKeySelective(operContext, tmpYjjData);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public TmpYjjData getById(Long id) {
        TmpYjjData tmpYjjData = super.selectByPrimaryKey(id);
        return tmpYjjData;
    }

    @Override
    public int addBatchYzt(String excelPath) {
        List<TmpYjjData> tmpYjjData = ReadExcelUtil.readXlsxToDbForjkyp(excelPath);
        int i = tmpYjjDataMapper.addBatch(tmpYjjData);
        return i;
    }
}