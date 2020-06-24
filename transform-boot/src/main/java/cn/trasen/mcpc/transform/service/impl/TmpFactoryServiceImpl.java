package cn.trasen.mcpc.transform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.TmpFactoryMapper;
import cn.trasen.mcpc.transform.model.TmpFactory;
import cn.trasen.mcpc.transform.service.TmpFactoryService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;

/**
 * 临时的 药品生产厂家服务实现.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class TmpFactoryServiceImpl extends BaseServiceImpl<TmpFactory> implements TmpFactoryService {   

    @Autowired
    private TmpFactoryMapper tmpFactoryMapper;

    @Override
    public Mapper<TmpFactory> getMapper() {
        return tmpFactoryMapper;
    }
    
    @Override
    public void add(OperContext operContext, TmpFactory tmpFactory) {
        super.insertSelective(operContext, tmpFactory);
    }

    @Override
    public void modifyById(OperContext operContext, TmpFactory tmpFactory) {
        super.updateByPrimaryKeySelective(operContext, tmpFactory);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public TmpFactory getById(Long id) {
        TmpFactory tmpFactory = super.selectByPrimaryKey(id);
        return tmpFactory;
    }
}