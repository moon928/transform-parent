package cn.trasen.mcpc.transform.dto;

import cn.trasen.mcpc.framework.base.BaseEntity;
import cn.trasen.mcpc.transform.model.TmpDictionary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 临时的 药品厂家表.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:44
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Setter
@Getter
public class TmpDictionaryDto extends TmpDictionary {
    private static final long serialVersionUID = 1L;
}