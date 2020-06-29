package cn.trasen.mcpc.transform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.trasen.mcpc.transform.dao.UscStatItemMapper;
import cn.trasen.mcpc.transform.model.UscStatItem;
import cn.trasen.mcpc.transform.service.UscStatItemService;
import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.framework.base.OperContext;
import tk.mybatis.mapper.common.Mapper;

/**
 * 大项目分类联动关系表服务实现.
 * 
 * @author Yan_zt
 * @date: 2020-06-29 18:32:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UscStatItemServiceImpl extends BaseServiceImpl<UscStatItem> implements UscStatItemService {   

    @Autowired
    private UscStatItemMapper uscStatItemMapper;

    @Override
    public Mapper<UscStatItem> getMapper() {
        return uscStatItemMapper;
    }
    
    @Override
    public void add(OperContext operContext, UscStatItem uscStatItem) {
        super.insertSelective(operContext, uscStatItem);
    }

    @Override
    public void modifyById(OperContext operContext, UscStatItem uscStatItem) {
        super.updateByPrimaryKeySelective(operContext, uscStatItem);
    }

    @Override
    public void removeById(OperContext operContext, Long id) {
        super.deleteByPrimaryKey(operContext, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public UscStatItem getById(Long id) {
        UscStatItem uscStatItem = super.selectByPrimaryKey(id);
        return uscStatItem;
    }
}