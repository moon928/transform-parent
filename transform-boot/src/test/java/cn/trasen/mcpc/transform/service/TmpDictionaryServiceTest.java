package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.trasen.mcpc.transform.model.TmpDictionary;
import cn.trasen.mcpc.framework.base.OperContext;

/**
 * 临时的 药品厂家表服务测试.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TmpDictionaryServiceTest {
    @Autowired
    private TmpDictionaryService tmpDictionaryService;

    @Test
    public void add() {
        TmpDictionary tmpDictionary = new TmpDictionary();
        tmpDictionaryService.add(OperContext.getSystemOperContext(), tmpDictionary);
    }

    @Test
    public void modifyById() {
        TmpDictionary tmpDictionary = new TmpDictionary();
        tmpDictionary.setId(null);
        tmpDictionaryService.modifyById(OperContext.getSystemOperContext(), tmpDictionary);
    }

    @Test
    public void removeById() {
        Long id = null;
        tmpDictionaryService.removeById(OperContext.getSystemOperContext(), id);
    }

    @Test
    public void getById() {
        Long id = null;
        TmpDictionary tmpDictionary = tmpDictionaryService.getById(id);
        Assert.assertNotNull(tmpDictionary);
    }
}