package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.TmpFactory;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 临时的 药品生产厂家服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TmpFactoryServiceTest {
    @Autowired
    private TmpFactoryService tmpFactoryService;

    @Test
    public void add() {
        TmpFactory tmpFactory = new TmpFactory();
        tmpFactoryService.add(OperContext.getSystemOperContext(), tmpFactory);
    }

    @Test
    public void modifyById() {
        TmpFactory tmpFactory = new TmpFactory();
        tmpFactory.setId(null);
        tmpFactoryService.modifyById(OperContext.getSystemOperContext(), tmpFactory);
    }

    @Test
    public void removeById() {
        Long id = null;
        tmpFactoryService.removeById(OperContext.getSystemOperContext(), id);
    }

    @Test
    public void getById() {
        Long id = null;
        TmpFactory tmpFactory = tmpFactoryService.getById(id);
        Assert.assertNotNull(tmpFactory);
    }
}