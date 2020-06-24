package cn.trasen.mcpc.transform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.TmpSpecsMapper;
import cn.trasen.mcpc.transform.model.TmpSpecs;
import cn.trasen.mcpc.transform.service.TmpSpecsService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;

/**
 * 临时的 药品规格表服务实现.
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
public class TmpSpecsServiceImpl extends BaseServiceImpl<TmpSpecs> implements TmpSpecsService {   

    @Autowired
    private TmpSpecsMapper tmpSpecsMapper;

    @Override
    public Mapper<TmpSpecs> getMapper() {
        return tmpSpecsMapper;
    }
    
    @Override
    public void add(OperContext operContext, TmpSpecs tmpSpecs) {
        super.insertSelective(operContext, tmpSpecs);
    }

    @Override
    public void modifyById(OperContext operContext, TmpSpecs tmpSpecs) {
        super.updateByPrimaryKeySelective(operContext, tmpSpecs);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public TmpSpecs getById(Long id) {
        TmpSpecs tmpSpecs = super.selectByPrimaryKey(id);
        return tmpSpecs;
    }
}