package cn.trasen.mcpc.transform.model;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 中文转拼音与五笔码测试表.
 * 
 * @author Yan_zt
 * @date: 2020-06-22 10:47:57
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Table(name = "t_test")
@Setter
@Getter
public class TTest extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 中文名称. */
    public static final String NAME = "name";
    /** 五笔码. */
    public static final String WB_CODE = "wbCode";
    /** 拼音码. */
    public static final String PY_CODE = "pyCode";

    /** 中文名称. */
    @Column(name = "NAME")
    @ApiModelProperty(value = "中文名称")
    private String name;

    /** 五笔码. */
    @Column(name = "WB_CODE")
    @ApiModelProperty(value = "五笔码")
    private String wbCode;

    /** 拼音码. */
    @Column(name = "PY_CODE")
    @ApiModelProperty(value = "拼音码")
    private String pyCode;
}