package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.UscStatItem;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 大项目分类联动关系表服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-29 18:32:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UscStatItemServiceTest {
    @Autowired
    private UscStatItemService uscStatItemService;

    @Test
    public void add() {
        UscStatItem uscStatItem = new UscStatItem();
        uscStatItemService.add(OperContext.getSystemOperContext(), uscStatItem);
    }

    @Test
    public void modifyById() {
        UscStatItem uscStatItem = new UscStatItem();
        uscStatItem.setId(null);
        uscStatItemService.modifyById(OperContext.getSystemOperContext(), uscStatItem);
    }

    @Test
    public void removeById() {
        Long id = null;
        uscStatItemService.removeById(OperContext.getSystemOperContext(), id);
    }

    @Test
    public void getById() {
        Long id = null;
        UscStatItem uscStatItem = uscStatItemService.getById(id);
        Assert.assertNotNull(uscStatItem);
    }
}