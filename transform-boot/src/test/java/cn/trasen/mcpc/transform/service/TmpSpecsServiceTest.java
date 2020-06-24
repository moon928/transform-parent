package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.TmpSpecs;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 临时的 药品规格表服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TmpSpecsServiceTest {
    @Autowired
    private TmpSpecsService tmpSpecsService;

    @Test
    public void add() {
        TmpSpecs tmpSpecs = new TmpSpecs();
        tmpSpecsService.add(OperContext.getSystemOperContext(), tmpSpecs);
    }

    @Test
    public void modifyById() {
        TmpSpecs tmpSpecs = new TmpSpecs();
        tmpSpecs.setId(null);
        tmpSpecsService.modifyById(OperContext.getSystemOperContext(), tmpSpecs);
    }

    @Test
    public void removeById() {
        Long id = null;
        tmpSpecsService.removeById(OperContext.getSystemOperContext(), id);
    }

    @Test
    public void getById() {
        Long id = null;
        TmpSpecs tmpSpecs = tmpSpecsService.getById(id);
        Assert.assertNotNull(tmpSpecs);
    }
}