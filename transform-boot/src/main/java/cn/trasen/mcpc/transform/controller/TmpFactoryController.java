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
import cn.trasen.mcpc.transform.model.TmpFactory;
import cn.trasen.mcpc.transform.service.TmpFactoryService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 临时的 药品生产厂家控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/tmpFactory")
@Api(tags = "临时的 药品生产厂家")
public class TmpFactoryController {

    @Autowired
    private TmpFactoryService tmpFactoryService;

    @ApiOperation(value = "临时的 药品生产厂家-新增", notes = "临时的 药品生产厂家-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody TmpFactory tmpFactory) {
        if (null == tmpFactory.getId()) {
            tmpFactoryService.add(OperContextUtil.getOperContext(), tmpFactory);
        } else {
            tmpFactoryService.modifyById(OperContextUtil.getOperContext(), tmpFactory);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "临时的 药品生产厂家-删除", notes = "临时的 药品生产厂家-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        tmpFactoryService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "临时的 药品生产厂家-根据ID获取", notes = "临时的 药品生产厂家-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<TmpFactory> getById(@PathVariable("id") Long id) {
        TmpFactory tmpFactory = tmpFactoryService.getById(id);
        return PlatformResult.success(tmpFactory);
    }
}