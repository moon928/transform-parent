package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.UscDrgFactory;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 药品生产厂家服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 17:44:15
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UscDrgFactoryServiceTest {
    @Autowired
    private UscDrgFactoryService uscDrgFactoryService;

    @Test
    public void add() {
        UscDrgFactory uscDrgFactory = new UscDrgFactory();
        uscDrgFactoryService.add(OperContext.getSystemOperContext(), uscDrgFactory);
    }

    @Test
    public void modifyById() {
        UscDrgFactory uscDrgFactory = new UscDrgFactory();
        uscDrgFactory.setId(null);
        uscDrgFactoryService.modifyById(OperContext.getSystemOperContext(), uscDrgFactory);
    }

    @Test
    public void removeById() {
        Long id = null;
        uscDrgFactoryService.removeById(OperContext.getSystemOperContext(), id);
    }

    @Test
    public void getById() {
        Long id = null;
        UscDrgFactory uscDrgFactory = uscDrgFactoryService.getById(id);
        Assert.assertNotNull(uscDrgFactory);
    }
}