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
import cn.trasen.mcpc.transform.model.TmpDictionary;
import cn.trasen.mcpc.transform.service.TmpDictionaryService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 临时的 药品厂家表控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/tmpDictionary")
@Api(tags = "临时的 药品厂家表")
public class TmpDictionaryController {

    @Autowired
    private TmpDictionaryService tmpDictionaryService;

    @ApiOperation(value = "临时的 药品厂家表-新增", notes = "临时的 药品厂家表-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody TmpDictionary tmpDictionary) {
        if (null == tmpDictionary.getId()) {
            tmpDictionaryService.add(OperContextUtil.getOperContext(), tmpDictionary);
        } else {
            tmpDictionaryService.modifyById(OperContextUtil.getOperContext(), tmpDictionary);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "临时的 药品厂家表-删除", notes = "临时的 药品厂家表-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        tmpDictionaryService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "临时的 药品厂家表-根据ID获取", notes = "临时的 药品厂家表-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<TmpDictionary> getById(@PathVariable("id") Long id) {
        TmpDictionary tmpDictionary = tmpDictionaryService.getById(id);
        return PlatformResult.success(tmpDictionary);
    }
}