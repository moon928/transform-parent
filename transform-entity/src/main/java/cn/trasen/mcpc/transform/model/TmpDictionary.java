package cn.trasen.mcpc.transform.model;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 临时的 药品厂家表.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Table(name = "tmp_dictionary")
@Setter
@Getter
public class TmpDictionary extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 药品规格ID. */
    public static final String SPECID = "specid";
    /** 商品名. */
    public static final String COMNAME = "comname";
    /** 药品本位码. */
    public static final String DRUGCODE_SPDA = "drugcodeSpda";
    /** 药品代码. */
    public static final String DRUGCODE_HPC = "drugcodeHpc";
    /** 批准文号. */
    public static final String APP_CODE = "appCode";
    /** 生产厂家ID. */
    public static final String FACT_ID = "factId";
    /** 备注. */
    public static final String MEMO = "memo";
    /** 是否发布  0-未发布 1-已发布. */
    public static final String PUBLISH = "publish";
    /** 状态  0-禁用  1-启用. */
    public static final String STATUS = "status";
    /** 删除标识 0-未删除 1-已删除. */
    public static final String DELETE_FLAG = "deleteFlag";

    /** 药品规格ID. */
    @Column(name = "SPECID")
    @ApiModelProperty(value = "药品规格ID")
    private String specid;

    /** 商品名. */
    @Column(name = "COMNAME")
    @ApiModelProperty(value = "商品名")
    private String comname;

    /** 药品本位码. */
    @Column(name = "DRUGCODE_SPDA")
    @ApiModelProperty(value = "药品本位码")
    private String drugcodeSpda;

    /** 药品代码. */
    @Column(name = "DRUGCODE_HPC")
    @ApiModelProperty(value = "药品代码")
    private String drugcodeHpc;

    /** 批准文号. */
    @Column(name = "APP_CODE")
    @ApiModelProperty(value = "批准文号")
    private String appCode;

    /** 生产厂家ID. */
    @Column(name = "FACT_ID")
    @ApiModelProperty(value = "生产厂家ID")
    private Long factId;

    /** 备注. */
    @Column(name = "MEMO")
    @ApiModelProperty(value = "备注")
    private String memo;

    /** 是否发布  0-未发布 1-已发布. */
    @Column(name = "PUBLISH")
    @ApiModelProperty(value = "是否发布  0-未发布 1-已发布")
    private String publish;

    /** 状态  0-禁用  1-启用. */
    @Column(name = "STATUS")
    @ApiModelProperty(value = "状态  0-禁用  1-启用")
    private String status;

    /** 删除标识 0-未删除 1-已删除. */
    @Column(name = "DELETE_FLAG")
    @ApiModelProperty(value = "删除标识 0-未删除 1-已删除")
    private String deleteFlag;
}