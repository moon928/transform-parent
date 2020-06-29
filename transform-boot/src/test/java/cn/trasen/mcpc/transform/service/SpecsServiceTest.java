package cn.trasen.mcpc.transform.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: project-parent
 * @description: SpecsService测试类
 * @author: yan_zt
 * @create: 2020-06-24 15:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpecsServiceTest {
    @Autowired
    private SpecsService specsService;
    @Test
    public void jkypSpecsTest(){
        String s = specsService.jkypSpecs("1");
        Assert.assertNotNull(s);
    }
}
