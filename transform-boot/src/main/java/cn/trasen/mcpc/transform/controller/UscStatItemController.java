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
import cn.trasen.mcpc.transform.model.UscStatItem;
import cn.trasen.mcpc.transform.service.UscStatItemService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 大项目分类联动关系表控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-29 18:32:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/uscStatItem")
@Api(tags = "大项目分类联动关系表")
public class UscStatItemController {

    @Autowired
    private UscStatItemService uscStatItemService;

    @ApiOperation(value = "大项目分类联动关系表-新增", notes = "大项目分类联动关系表-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody UscStatItem uscStatItem) {
        if (null == uscStatItem.getId()) {
            uscStatItemService.add(OperContextUtil.getOperContext(), uscStatItem);
        } else {
            uscStatItemService.modifyById(OperContextUtil.getOperContext(), uscStatItem);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "大项目分类联动关系表-删除", notes = "大项目分类联动关系表-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        uscStatItemService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "大项目分类联动关系表-根据ID获取", notes = "大项目分类联动关系表-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<UscStatItem> getById(@PathVariable("id") Long id) {
        UscStatItem uscStatItem = uscStatItemService.getById(id);
        return PlatformResult.success(uscStatItem);
    }
}