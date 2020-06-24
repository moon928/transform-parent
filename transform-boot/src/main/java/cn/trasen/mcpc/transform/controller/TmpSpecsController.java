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
import cn.trasen.mcpc.transform.model.TmpSpecs;
import cn.trasen.mcpc.transform.service.TmpSpecsService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 临时的 药品规格表控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/tmpSpecs")
@Api(tags = "临时的 药品规格表")
public class TmpSpecsController {

    @Autowired
    private TmpSpecsService tmpSpecsService;

    @ApiOperation(value = "临时的 药品规格表-新增", notes = "临时的 药品规格表-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody TmpSpecs tmpSpecs) {
        if (null == tmpSpecs.getId()) {
            tmpSpecsService.add(OperContextUtil.getOperContext(), tmpSpecs);
        } else {
            tmpSpecsService.modifyById(OperContextUtil.getOperContext(), tmpSpecs);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "临时的 药品规格表-删除", notes = "临时的 药品规格表-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        tmpSpecsService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "临时的 药品规格表-根据ID获取", notes = "临时的 药品规格表-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<TmpSpecs> getById(@PathVariable("id") Long id) {
        TmpSpecs tmpSpecs = tmpSpecsService.getById(id);
        return PlatformResult.success(tmpSpecs);
    }
}