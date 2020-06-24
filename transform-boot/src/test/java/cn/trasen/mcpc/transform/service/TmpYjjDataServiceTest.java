package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.TmpYjjData;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 药监局数据临时表服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 13:23:59
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TmpYjjDataServiceTest {
    @Autowired
    private TmpYjjDataService tmpYjjDataService;

    @Test
    public void add() {
        TmpYjjData tmpYjjData = new TmpYjjData();
        tmpYjjDataService.add(OperContext.getSystemOperContext(), tmpYjjData);
    }

    @Test
    public void modifyById() {
        TmpYjjData tmpYjjData = new TmpYjjData();
        tmpYjjData.setId(null);
        tmpYjjDataService.modifyById(OperContext.getSystemOperContext(), tmpYjjData);
    }

    @Test
    public void removeById() {
        Long id = null;
        tmpYjjDataService.removeById(OperContext.getSystemOperContext(), id);
    }

    @Test
    public void getById() {
        Long id = null;
        TmpYjjData tmpYjjData = tmpYjjDataService.getById(id);
        Assert.assertNotNull(tmpYjjData);
    }
}