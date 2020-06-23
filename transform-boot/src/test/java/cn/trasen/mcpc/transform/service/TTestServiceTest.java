package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 中文转拼音与五笔码测试表服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TTestServiceTest {
    @Autowired
    private TTestService tTestService;

//    @Test
//    public void add() {
//        TTest tTest = new TTest();
//        tTestService.add(OperContext.getSystemOperContext(), tTest);
//    }
//
//    @Test
//    public void modifyById() {
//        TTest tTest = new TTest();
//        tTest.setId(null);
//        tTestService.modifyById(OperContext.getSystemOperContext(), tTest);
//    }
//
//    @Test
//    public void removeById() {
//        Long id = null;
//        tTestService.removeById(OperContext.getSystemOperContext(), id);
//    }
//
//    @Test
//    public void getById() {
//        Long id = null;
//        TTest tTest = tTestService.getById(id);
//        Assert.assertNotNull(tTest);
//    }
}