package cn.trasen.mcpc.transform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.UscDrgFactoryMapper;
import cn.trasen.mcpc.transform.model.UscDrgFactory;
import cn.trasen.mcpc.transform.service.UscDrgFactoryService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;

/**
 * 药品生产厂家服务实现.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 17:44:15
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UscDrgFactoryServiceImpl extends BaseServiceImpl<UscDrgFactory> implements UscDrgFactoryService {   

    @Autowired
    private UscDrgFactoryMapper uscDrgFactoryMapper;

    @Override
    public Mapper<UscDrgFactory> getMapper() {
        return uscDrgFactoryMapper;
    }
    
    @Override
    public void add(OperContext operContext, UscDrgFactory uscDrgFactory) {
        super.insertSelective(operContext, uscDrgFactory);
    }

    @Override
    public void modifyById(OperContext operContext, UscDrgFactory uscDrgFactory) {
        super.updateByPrimaryKeySelective(operContext, uscDrgFactory);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public UscDrgFactory getById(Long id) {
        UscDrgFactory uscDrgFactory = super.selectByPrimaryKey(id);
        return uscDrgFactory;
    }
}