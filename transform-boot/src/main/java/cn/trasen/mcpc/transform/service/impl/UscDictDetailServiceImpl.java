package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.transform.dao.UscDictDetailMapper;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.model.UscDictDetail;
import cn.trasen.mcpc.transform.service.TTestService;
import cn.trasen.mcpc.transform.service.UscDictDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: project-parent
 * @description: 字典明细Service实现类
 * @author: yan_zt
 * @create: 2020-06-23 16:37
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UscDictDetailServiceImpl extends BaseServiceImpl<UscDictDetail> implements UscDictDetailService {

    @Autowired
    private UscDictDetailMapper uscDictDetailMapper;
    @Override
    public Mapper<UscDictDetail> getMapper() {
        return uscDictDetailMapper;
    }

    @Override
    public List<UscDictDetail> list(String catalogCode) {
        Example example = new Example(UscDictDetail.class);
        Example.Criteria criteria = example.createCriteria();
        if (catalogCode != null && !"".equals(catalogCode)){
            criteria.andEqualTo(UscDictDetail.CATALOG_CODE,catalogCode);
        }
        example.setOrderByClause("ORDER_NUM");
        List<UscDictDetail> basDictDetails = super.selectByExample(example);
        return basDictDetails;
    }
}
