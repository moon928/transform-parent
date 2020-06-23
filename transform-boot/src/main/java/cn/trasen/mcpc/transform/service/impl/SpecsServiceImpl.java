package cn.trasen.mcpc.transform.service.impl;

import cn.trasen.mcpc.framework.base.BaseServiceImpl;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.model.UscDictDetail;
import cn.trasen.mcpc.transform.model.UscDrgSpecs;
import cn.trasen.mcpc.transform.service.SpecsService;
import cn.trasen.mcpc.transform.service.TTestService;
import cn.trasen.mcpc.transform.service.UscDictDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: project-parent
 * @description: SpecsService实现类
 * @author: yan_zt
 * @create: 2020-06-23 16:32
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SpecsServiceImpl extends BaseServiceImpl<UscDrgSpecs> implements SpecsService {

    private Map<String,String> detailMap = new HashMap<>();

    @Autowired
    private UscDictDetailService uscDictDetailService;

    @Override
    public Mapper<UscDrgSpecs> getMapper() {
        return null;
    }

    void initializeDetail(){
        List<UscDictDetail> list = uscDictDetailService.list(null);
        for (UscDictDetail uscDictDetail : list){
//            detailMap.put(uscDictDetail.getCode()+uscDictDetail.getCatalogCode(),uscDictDetail.getName());
            detailMap.put(uscDictDetail.getName()+uscDictDetail.getCatalogCode(),uscDictDetail.getCode());
        }
    }

    /**
     * 判断剂型是否在存在字典中
     * @param name 名称
     * @param catalogCode 字典类型 剂型-DOSFORM  药品类型-drug_type
     * @return
     */
    public String isHave(String name,String catalogCode){
        if (detailMap.size() == 0){
            initializeDetail();
        }
        String s = detailMap.get(name+catalogCode);
        return s;
    }


    @Override
    public String test() {
        String have1 = isHave("片剂", "DOSFORM");
        String have2 = isHave("片剂aa", "DOSFORM");
        String have3 = isHave("西药", "drug_type");
        System.out.println(have1);
        System.out.println(have2);
        System.out.println(have3);
        return "ok";
    }
}
