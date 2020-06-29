package cn.trasen.mcpc.transform.dto;

import cn.trasen.mcpc.transform.model.UscDrgSpecs;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: project-parent
 * @description: UscDrgSpecsDto
 * @author: yan_zt
 * @create: 2020-06-23 19:51
 */
@Getter
@Setter
@ToString
public class UscDrgSpecsDto extends UscDrgSpecs {

    /** 是否成功 0-不成功 1-成功的但不新增 2-成功的，同时要新增*/
    private Long isSuccess;

    /**读取错误信息*/
    private String readError;

    /**商品名*/
    private String comName;

    /**生产厂家名称*/
    private String factName;

    /**药品本位码*/
    private String drugCodeSpda;

    /**批准文号*/
    private String appCode;

    /**生产厂家id*/
    private Long factId;

    /**生产厂家联系地址*/
    private String relAddr;
}
