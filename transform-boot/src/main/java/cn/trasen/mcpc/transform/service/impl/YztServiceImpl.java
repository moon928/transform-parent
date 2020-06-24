package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.transform.dao.TmpYjjDataMapper;
import cn.trasen.mcpc.transform.model.UscDictDetail;
import cn.trasen.mcpc.transform.model.UscDrgSpecs;
import cn.trasen.mcpc.transform.service.UscDictDetailService;
import cn.trasen.mcpc.transform.service.YztService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: project-parent
 * @description: YztService实现类
 * @author: yan_zt
 * @create: 2020-06-24 14:40
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class YztServiceImpl extends BaseServiceImpl<UscDrgSpecs> implements YztService {

    @Autowired
    private TmpYjjDataMapper yjjDataMapper;
    @Override
    public Mapper<UscDrgSpecs> getMapper() {
        return null;
    }


}
