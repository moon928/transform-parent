package cn.trasen.mcpc.transform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.TmpDictionaryMapper;
import cn.trasen.mcpc.transform.model.TmpDictionary;
import cn.trasen.mcpc.transform.service.TmpDictionaryService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;

/**
 * 临时的 药品厂家表服务实现.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class TmpDictionaryServiceImpl extends BaseServiceImpl<TmpDictionary> implements TmpDictionaryService {   

    @Autowired
    private TmpDictionaryMapper tmpDictionaryMapper;

    @Override
    public Mapper<TmpDictionary> getMapper() {
        return tmpDictionaryMapper;
    }
    
    @Override
    public void add(OperContext operContext, TmpDictionary tmpDictionary) {
        super.insertSelective(operContext, tmpDictionary);
    }

    @Override
    public void modifyById(OperContext operContext, TmpDictionary tmpDictionary) {
        super.updateByPrimaryKeySelective(operContext, tmpDictionary);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public TmpDictionary getById(Long id) {
        TmpDictionary tmpDictionary = super.selectByPrimaryKey(id);
        return tmpDictionary;
    }
}