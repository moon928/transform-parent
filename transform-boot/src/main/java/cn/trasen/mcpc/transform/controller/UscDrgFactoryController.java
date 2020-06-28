package cn.trasen.mcpc.transform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.trasen.BootComm.utils.PlatformResult;
import cn.trasen.mcpc.transform.model.UscDrgFactory;
import cn.trasen.mcpc.transform.service.UscDrgFactoryService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 药品生产厂家控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 17:44:15
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/uscDrgFactory")
@Api(tags = "药品生产厂家")
public class UscDrgFactoryController {

    @Autowired
    private UscDrgFactoryService uscDrgFactoryService;

    @ApiOperation(value = "药品生产厂家-新增", notes = "药品生产厂家-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody UscDrgFactory uscDrgFactory) {
        if (null == uscDrgFactory.getId()) {
            uscDrgFactoryService.add(OperContextUtil.getOperContext(), uscDrgFactory);
        } else {
            uscDrgFactoryService.modifyById(OperContextUtil.getOperContext(), uscDrgFactory);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "药品生产厂家-删除", notes = "药品生产厂家-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        uscDrgFactoryService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "药品生产厂家-根据ID获取", notes = "药品生产厂家-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<UscDrgFactory> getById(@PathVariable("id") Long id) {
        UscDrgFactory uscDrgFactory = uscDrgFactoryService.getById(id);
        return PlatformResult.success(uscDrgFactory);
    }
}