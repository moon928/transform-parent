package cn.trasen.mcpc.transform.dto;

import cn.trasen.mcpc.framework.base.BaseEntity;
import cn.trasen.mcpc.transform.model.TmpFactory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 临时的 药品生产厂家.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Setter
@Getter
public class TmpFactoryDto extends TmpFactory {
    private static final long serialVersionUID = 1L;

    /**是否新增  0-不新增 1-新增 -1-错误*/
    private Long isHave;

    /**错误信息*/
    private String readError;
}