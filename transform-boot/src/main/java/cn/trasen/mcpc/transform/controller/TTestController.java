package cn.trasen.mcpc.transform.controller;

import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.feature.orm.mybatis.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.trasen.BootComm.utils.PlatformResult;
import cn.trasen.mcpc.transform.model.TTest;
import cn.trasen.mcpc.transform.service.TTestService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@RequestMapping("/tTest")
@Api(tags = "中文转拼音与五笔码测试表")
public class TTestController {

    @Autowired
    private TTestService tTestService;

    @ApiOperation(value = "中文转拼音与五笔码测试表-新增", notes = "中文转拼音与五笔码测试表-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody TTest tTest) {
        if (null == tTest.getId()) {
            tTestService.add(OperContextUtil.getOperContext(), tTest);
        } else {
            tTestService.modifyById(OperContextUtil.getOperContext(), tTest);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "中文转拼音与五笔码测试表-删除", notes = "中文转拼音与五笔码测试表-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        tTestService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "中文转拼音与五笔码测试表-根据ID获取", notes = "中文转拼音与五笔码测试表-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<TTest> getById(@PathVariable("id") Long id) {
        TTest tTest = tTestService.getById(id);
        return PlatformResult.success(tTest);
    }

    @ApiOperation(value = "中文转拼音与五笔码测试表-分页获取", notes = "中文转拼音与五笔码测试表-分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "查询name条件", paramType = "query", dataType = "String")})
    @GetMapping("/page")
    public PlatformResult<DataSet<TTest>> page(@RequestParam("query") String query, Page page) {
        DataSet<TTest> dataSet = tTestService.page(query, page);
        return PlatformResult.success(dataSet);
    }

    @ApiOperation(value = "中文转拼音与五笔码测试表-中文转拼音和五笔", notes = "中文转拼音与五笔码测试表-中文转拼音和五笔")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "所要转的表名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "columnName", value = "所要转化表中的列名", paramType = "query", dataType = "String")})
    @PostMapping("/chineseToPyAndWb")
    public PlatformResult<String> chineseToPyAndWb(@RequestParam("tableName") String tableName,@RequestParam("columnName") String columnName) {
        try{
            int i = tTestService.chineseToPyAndWb(tableName,columnName);
            return PlatformResult.success("一共修改 "+tableName+" 表 "+i+" 行数据");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return PlatformResult.failure(e.getMessage());
        }
    }
}