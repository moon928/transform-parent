package cn.trasen.mcpc.transform.model;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * @program: project-parent
 * @description: 转拼音和五笔Dto
 * @author: yan_zt
 * @create: 2020-06-22 15:15
 */
@Getter
@Setter
public class ToPyAndWbDto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 中文名称. */
    @ApiModelProperty(value = "中文名称")
    private String name;

    /** 五笔码. */
    @ApiModelProperty(value = "五笔码")
    private String wbCode;

    /** 拼音码. */
    @ApiModelProperty(value = "拼音码")
    private String pyCode;
}
