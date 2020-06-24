package cn.trasen.mcpc.transform.model;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 临时的 药品生产厂家.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Table(name = "tmp_factory")
@Setter
@Getter
public class TmpFactory extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 生产厂家名称. */
    public static final String FACT_NAME = "factName";
    /** 五笔码. */
    public static final String WB_CODE = "wbCode";
    /** 拼音码. */
    public static final String PY_CODE = "pyCode";
    /** 联系电话. */
    public static final String REL_TEL = "relTel";
    /** 联系人. */
    public static final String REL_MAN = "relMan";
    /** 联系地址. */
    public static final String REL_ADDR = "relAddr";
    /** 是否GMP达标. */
    public static final String ISGMP = "isgmp";
    /** 是否发布 0-未发布 1-已发布. */
    public static final String PUBLISH = "publish";
    /** 状态  0-禁用  1-启用. */
    public static final String STATUS = "status";
    /** 删除标识 0-未删除 1-已删除. */
    public static final String DELETE_FLAG = "deleteFlag";

    /** 生产厂家名称. */
    @Column(name = "FACT_NAME")
    @ApiModelProperty(value = "生产厂家名称")
    private String factName;

    /** 五笔码. */
    @Column(name = "WB_CODE")
    @ApiModelProperty(value = "五笔码")
    private String wbCode;

    /** 拼音码. */
    @Column(name = "PY_CODE")
    @ApiModelProperty(value = "拼音码")
    private String pyCode;

    /** 联系电话. */
    @Column(name = "REL_TEL")
    @ApiModelProperty(value = "联系电话")
    private String relTel;

    /** 联系人. */
    @Column(name = "REL_MAN")
    @ApiModelProperty(value = "联系人")
    private String relMan;

    /** 联系地址. */
    @Column(name = "REL_ADDR")
    @ApiModelProperty(value = "联系地址")
    private String relAddr;

    /** 是否GMP达标. */
    @Column(name = "ISGMP")
    @ApiModelProperty(value = "是否GMP达标")
    private Integer isgmp;

    /** 是否发布 0-未发布 1-已发布. */
    @Column(name = "PUBLISH")
    @ApiModelProperty(value = "是否发布 0-未发布 1-已发布")
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