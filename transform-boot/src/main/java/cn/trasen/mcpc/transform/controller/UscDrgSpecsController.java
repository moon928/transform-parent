package cn.trasen.mcpc.transform.controller;

import cn.trasen.BootComm.model.DataSet;
import cn.trasen.BootComm.utils.PlatformResult;
import cn.trasen.core.feature.orm.mybatis.Page;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.service.SpecsService;
import cn.trasen.mcpc.transform.service.TTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 中文转拼音与五笔码测试表控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/drug")
@Api(tags = "药品数据导入")
public class UscDrgSpecsController {

    @Autowired
    private SpecsService specsService;

    @ApiOperation(value = "药品数据导入-ceshi", notes = "药品数据导入-ceshi")
    @PostMapping("/test")
    public PlatformResult<String> chineseToPyAndWb() {
        try{
            String test = specsService.test();
            return PlatformResult.success(test);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return PlatformResult.failure(e.getMessage());
        }
    }


    @ApiOperation(value = "药品数据导入-导入药品规格数据", notes = "药品数据导入-导入药品规格数据")
    @PostMapping("/specs")
    public PlatformResult<String> specs() {
        try{
            String s = specsService.jkypSpecs();
            return PlatformResult.success(s);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return PlatformResult.failure(e.getMessage());
        }
    }
}