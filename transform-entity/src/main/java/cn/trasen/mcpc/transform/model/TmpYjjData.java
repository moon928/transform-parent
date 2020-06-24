package cn.trasen.mcpc.transform.model;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 药监局数据临时表.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 13:23:59
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Table(name = "tmp_yjj_data")
@Setter
@Getter
public class TmpYjjData extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 产品名称. */
    public static final String CHEMICAL_NAME = "chemicalName";
    /** 产品英文名称. */
    public static final String ENG_NAME = "engName";
    /** 药品剂型 字典：DOSFORM. */
    public static final String DRUG_DOSFORM_CODE = "drugDosformCode";
    /** 药品类型 字典：drug_type. */
    public static final String DRUG_TYPE = "drugType";
    /** 注册规格. */
    public static final String SPEC_DESC = "specDesc";
    /** 版本id. */
    public static final String VERSION_ID = "versionId";
    /** 商品名称. */
    public static final String COMNAME = "comname";
    /** 药品本位码. */
    public static final String DRUGCODE_SPDA = "drugcodeSpda";
    /** 批准文号. */
    public static final String APP_CODE = "appCode";
    /** 生产厂家名称. */
    public static final String FACT_NAME = "factName";
    /** 联系地址. */
    public static final String REL_ADDR = "relAddr";
    /** 是否国产 0-否 1-是. */
    public static final String DOMESTIC = "domestic";
    /** 是否进口 0-否 1-是. */
    public static final String IMPORTED = "imported";

    /** 产品名称. */
    @Column(name = "CHEMICAL_NAME")
    @ApiModelProperty(value = "产品名称")
    private String chemicalName;

    /** 产品英文名称. */
    @Column(name = "ENG_NAME")
    @ApiModelProperty(value = "产品英文名称")
    private String engName;

    /** 药品剂型 字典：DOSFORM. */
    @Column(name = "DRUG_DOSFORM_CODE")
    @ApiModelProperty(value = "药品剂型 字典：DOSFORM")
    private String drugDosformCode;

    /** 药品类型 字典：drug_type. */
    @Column(name = "DRUG_TYPE")
    @ApiModelProperty(value = "药品类型 字典：drug_type")
    private String drugType;

    /** 注册规格. */
    @Column(name = "SPEC_DESC")
    @ApiModelProperty(value = "注册规格")
    private String specDesc;

    /** 版本id. */
    @Column(name = "VERSION_ID")
    @ApiModelProperty(value = "版本id")
    private Long versionId;

    /** 商品名称. */
    @Column(name = "COMNAME")
    @ApiModelProperty(value = "商品名称")
    private String comname;

    /** 药品本位码. */
    @Column(name = "DRUGCODE_SPDA")
    @ApiModelProperty(value = "药品本位码")
    private String drugcodeSpda;

    /** 批准文号. */
    @Column(name = "APP_CODE")
    @ApiModelProperty(value = "批准文号")
    private String appCode;

    /** 生产厂家名称. */
    @Column(name = "FACT_NAME")
    @ApiModelProperty(value = "生产厂家名称")
    private String factName;

    /** 联系地址. */
    @Column(name = "REL_ADDR")
    @ApiModelProperty(value = "联系地址")
    private String relAddr;

    /** 是否国产 0-否 1-是. */
    @Column(name = "DOMESTIC")
    @ApiModelProperty(value = "是否国产 0-否 1-是")
    private Integer domestic;

    /** 是否进口 0-否 1-是. */
    @Column(name = "IMPORTED")
    @ApiModelProperty(value = "是否进口 0-否 1-是")
    private Integer imported;
}