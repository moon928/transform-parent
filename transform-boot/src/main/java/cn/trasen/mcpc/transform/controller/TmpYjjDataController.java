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
import cn.trasen.mcpc.transform.model.TmpYjjData;
import cn.trasen.mcpc.transform.service.TmpYjjDataService;
import cn.trasen.mcpc.framework.util.OperContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 药监局数据临时表控制器.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 13:23:59
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/tmpYjjData")
@Api(tags = "药监局数据临时表")
public class TmpYjjDataController {

    @Autowired
    private TmpYjjDataService tmpYjjDataService;

    @ApiOperation(value = "药监局数据临时表-新增", notes = "药监局数据临时表-新增")
    @PostMapping("/save")
    public PlatformResult<String> save(@RequestBody TmpYjjData tmpYjjData) {
        if (null == tmpYjjData.getId()) {
            tmpYjjDataService.add(OperContextUtil.getOperContext(), tmpYjjData);
        } else {
            tmpYjjDataService.modifyById(OperContextUtil.getOperContext(), tmpYjjData);
        }

        return PlatformResult.success();
    }

    @ApiOperation(value = "药监局数据临时表-删除", notes = "药监局数据临时表-刪除")
    @PostMapping("/remove/{id}")
    public PlatformResult<String> removeById(@PathVariable("id") Long id) {
        tmpYjjDataService.removeById(OperContextUtil.getOperContext(), id);
        return PlatformResult.success();
    }

    @ApiOperation(value = "药监局数据临时表-根据ID获取", notes = "药监局数据临时表-根据ID获取")
    @GetMapping("/{id}")
    public PlatformResult<TmpYjjData> getById(@PathVariable("id") Long id) {
        TmpYjjData tmpYjjData = tmpYjjDataService.getById(id);
        return PlatformResult.success(tmpYjjData);
    }
}