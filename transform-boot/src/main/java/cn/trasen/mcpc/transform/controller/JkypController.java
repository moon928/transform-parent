package cn.trasen.mcpc.transform.controller;

import cn.trasen.BootComm.utils.PlatformResult;
import cn.trasen.mcpc.transform.service.SpecsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 进口药品Controller
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/jkyp")
@Api(tags = "进口药品excel数据转sql")
public class JkypController {

    @Autowired
    private SpecsService specsService;

    @ApiOperation(value = "进口药品excel数据转sql", notes = "进口药品excel数据转sql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "excelPath", value = "所要读取的excel地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sqlPath", value = "sql存入地址", paramType = "query", dataType = "String")})
    @PostMapping("/toSql")
    public PlatformResult<String> toSql(@RequestParam("excelPath") String excelPath, @RequestParam("sqlPath") String sqlPath) {
        try{
            String s = specsService.jkypToSql(excelPath, sqlPath);
            return PlatformResult.success(s);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return PlatformResult.failure(e.getMessage());
        }
    }
}