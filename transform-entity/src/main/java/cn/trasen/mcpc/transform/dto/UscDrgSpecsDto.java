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
    /**读取错误信息*/
    private String readError;

}
