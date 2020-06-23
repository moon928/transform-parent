package cn.trasen.mcpc.transform.model;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 字典明细.
 * 
 * @author zhaozq
 * @date: 2020-05-26 14:32:17
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Table(name = "usc_dict_detail")
@Setter
@Getter
@ToString
public class UscDictDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 编码. */
    public static final String CODE = "code";
    /** 名称. */
    public static final String NAME = "name";
    /** 五笔码. */
    public static final String WB_CODE = "wbCode";
    /** 拼音码. */
    public static final String PY_CODE = "pyCode";
    /** 分类编码. */
    public static final String CATALOG_CODE = "catalogCode";
    /** 分类名称. */
    public static final String CATALOG_NAME = "catalogName";
    /** 排序字段. */
    public static final String ORDER_NUM = "orderNum";
    /** 是否发布 0-未发布 1-已发布. */
    public static final String PUBLISH = "publish";
    /** 状态  0-禁用  1-启用. */
    public static final String STATUS = "status";
    /** 删除标识 0-未删除 1-已删除. */
    public static final String DELETE_FLAG = "deleteFlag";


    /** 编码. */
    @Column(name = "CODE")
    @ApiModelProperty(value = "编码")
    private String code;

    /** 名称. */
    @Column(name = "NAME")
    @ApiModelProperty(value = "名称")
    private String name;

    /** 五笔码. */
    @Column(name = "WB_CODE")
    @ApiModelProperty(value = "五笔码")
    private String wbCode;

    /** 拼音码. */
    @Column(name = "PY_CODE")
    @ApiModelProperty(value = "拼音码")
    private String pyCode;

    /** 分类编码. */
    @Column(name = "CATALOG_CODE")
    @ApiModelProperty(value = "分类编码")
    private String catalogCode;

    /** 分类名称. */
    @Column(name = "CATALOG_NAME")
    @ApiModelProperty(value = "分类名称")
    private String catalogName;

    /** 排序字段. */
    @Column(name = "ORDER_NUM")
    @ApiModelProperty(value = "排序字段")
    private Integer orderNum;

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