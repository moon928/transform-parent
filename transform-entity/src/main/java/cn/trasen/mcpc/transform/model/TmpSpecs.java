package cn.trasen.mcpc.transform.model;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.trasen.mcpc.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 临时的 药品规格表.
 * 
 * @author Yan_zt
 * @date: 2020-06-24 16:45:45
 * @Copyright: Copyright (c) 2006 - 2020
 * @Company: 湖南创星科技股份有限公司
 * @Version: V1.0
 */
@Table(name = "tmp_specs")
@Setter
@Getter
public class TmpSpecs extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 药品编码. */
    public static final String STANDARD_CODE = "standardCode";
    /** 注册名称. */
    public static final String CHEMICAL_NAME = "chemicalName";
    /** 药品剂型 字典：DOSFORM. */
    public static final String DRUG_DOSFORM_CODE = "drugDosformCode";
    /** 药品类型 字典：drug_type. */
    public static final String DRUG_TYPE = "drugType";
    /** 英文名称. */
    public static final String ENG_NANE = "engNane";
    /** 拼音码. */
    public static final String PY_CODE = "pyCode";
    /** 五笔码. */
    public static final String WB_CODE = "wbCode";
    /** 注册规格. */
    public static final String SPEC_DESC = "specDesc";
    /** 剂量. */
    public static final String DOSE = "dose";
    /** 剂量单位 字典:YPDW_001. */
    public static final String DOSEUNIT_CODE = "doseunitCode";
    /** 含量. */
    public static final String CONTENT = "content";
    /** 含量单位 字典:YPDW_001. */
    public static final String CONTENTUNIT_CODE = "contentunitCode";
    /** 包装数量. */
    public static final String PACKNUM = "packnum";
    /** 包装单位 字典:YPDW_001. */
    public static final String PACKUNIT_CODE = "packunitCode";
    /** 包装材质 字典:PACK_MATERIAL. */
    public static final String PACK_MATERIAL = "packMaterial";
    /** 单位 字典:YPDW_001. */
    public static final String UNIT = "unit";
    /** 抗生素等级 字典:pub_level. */
    public static final String ANTI_GRADE_CODE = "antiGradeCode";
    /** DDD值. */
    public static final String DDD_VALUE = "dddValue";
    /** DDD剂量. */
    public static final String DDD_DOSE = "dddDose";
    /** 备注. */
    public static final String MEMO = "memo";
    /** 是否国产 0-否 1-是. */
    public static final String DOMESTIC = "domestic";
    /** 是否进口 0-否 1-是. */
    public static final String IMPORTED = "imported";
    /** 版本id. */
    public static final String VERSION_ID = "versionId";
    /** 引用药品ID. */
    public static final String QUOTE_DRG_ID = "quoteDrgId";
    /** 甲乙类  字典：PRODUCT_INS_TYPE. */
    public static final String PRODUCT_INS_TYPE = "productInsType";
    /** 医保编号. */
    public static final String PRODUCT_CODE = "productCode";
    /** 医保药品名称. */
    public static final String PRODUCT_NAME = "productName";
    /** 医保剂型 字典:PRODUCT_MODEL. */
    public static final String PRODUCT_MODEL = "productModel";
    /** 医保备注. */
    public static final String PRODUCT_REMARK = "productRemark";
    /** 申请机构编码. */
    public static final String ORG_CODE = "orgCode";
    /** 状态  0-禁用  1-启用. */
    public static final String STATUS = "status";
    /** 删除标识 0-未删除 1-已删除. */
    public static final String DELETE_FLAG = "deleteFlag";

    /** 药品编码. */
    @Column(name = "STANDARD_CODE")
    @ApiModelProperty(value = "药品编码")
    private String standardCode;

    /** 注册名称. */
    @Column(name = "CHEMICAL_NAME")
    @ApiModelProperty(value = "注册名称")
    private String chemicalName;

    /** 药品剂型 字典：DOSFORM. */
    @Column(name = "DRUG_DOSFORM_CODE")
    @ApiModelProperty(value = "药品剂型 字典：DOSFORM")
    private String drugDosformCode;

    /** 药品类型 字典：drug_type. */
    @Column(name = "DRUG_TYPE")
    @ApiModelProperty(value = "药品类型 字典：drug_type")
    private String drugType;

    /** 英文名称. */
    @Column(name = "ENG_NANE")
    @ApiModelProperty(value = "英文名称")
    private String engNane;

    /** 拼音码. */
    @Column(name = "PY_CODE")
    @ApiModelProperty(value = "拼音码")
    private String pyCode;

    /** 五笔码. */
    @Column(name = "WB_CODE")
    @ApiModelProperty(value = "五笔码")
    private String wbCode;

    /** 注册规格. */
    @Column(name = "SPEC_DESC")
    @ApiModelProperty(value = "注册规格")
    private String specDesc;

    /** 剂量. */
    @Column(name = "DOSE")
    @ApiModelProperty(value = "剂量")
    private Double dose;

    /** 剂量单位 字典:YPDW_001. */
    @Column(name = "DOSEUNIT_CODE")
    @ApiModelProperty(value = "剂量单位 字典:YPDW_001")
    private String doseunitCode;

    /** 含量. */
    @Column(name = "CONTENT")
    @ApiModelProperty(value = "含量")
    private Double content;

    /** 含量单位 字典:YPDW_001. */
    @Column(name = "CONTENTUNIT_CODE")
    @ApiModelProperty(value = "含量单位 字典:YPDW_001")
    private String contentunitCode;

    /** 包装数量. */
    @Column(name = "PACKNUM")
    @ApiModelProperty(value = "包装数量")
    private Integer packnum;

    /** 包装单位 字典:YPDW_001. */
    @Column(name = "PACKUNIT_CODE")
    @ApiModelProperty(value = "包装单位 字典:YPDW_001")
    private String packunitCode;

    /** 包装材质 字典:PACK_MATERIAL. */
    @Column(name = "PACK_MATERIAL")
    @ApiModelProperty(value = "包装材质 字典:PACK_MATERIAL")
    private String packMaterial;

    /** 单位 字典:YPDW_001. */
    @Column(name = "UNIT")
    @ApiModelProperty(value = "单位 字典:YPDW_001")
    private String unit;

    /** 抗生素等级 字典:pub_level. */
    @Column(name = "ANTI_GRADE_CODE")
    @ApiModelProperty(value = "抗生素等级 字典:pub_level")
    private String antiGradeCode;

    /** DDD值. */
    @Column(name = "DDD_VALUE")
    @ApiModelProperty(value = "DDD值")
    private Integer dddValue;

    /** DDD剂量. */
    @Column(name = "DDD_DOSE")
    @ApiModelProperty(value = "DDD剂量")
    private Integer dddDose;

    /** 备注. */
    @Column(name = "MEMO")
    @ApiModelProperty(value = "备注")
    private String memo;

    /** 是否国产 0-否 1-是. */
    @Column(name = "DOMESTIC")
    @ApiModelProperty(value = "是否国产 0-否 1-是")
    private Integer domestic;

    /** 是否进口 0-否 1-是. */
    @Column(name = "IMPORTED")
    @ApiModelProperty(value = "是否进口 0-否 1-是")
    private Integer imported;

    /** 版本id. */
    @Column(name = "VERSION_ID")
    @ApiModelProperty(value = "版本id")
    private Long versionId;

    /** 引用药品ID. */
    @Column(name = "QUOTE_DRG_ID")
    @ApiModelProperty(value = "引用药品ID")
    private Long quoteDrgId;

    /** 甲乙类  字典：PRODUCT_INS_TYPE. */
    @Column(name = "PRODUCT_INS_TYPE")
    @ApiModelProperty(value = "甲乙类  字典：PRODUCT_INS_TYPE")
    private String productInsType;

    /** 医保编号. */
    @Column(name = "PRODUCT_CODE")
    @ApiModelProperty(value = "医保编号")
    private String productCode;

    /** 医保药品名称. */
    @Column(name = "PRODUCT_NAME")
    @ApiModelProperty(value = "医保药品名称")
    private String productName;

    /** 医保剂型 字典:PRODUCT_MODEL. */
    @Column(name = "PRODUCT_MODEL")
    @ApiModelProperty(value = "医保剂型 字典:PRODUCT_MODEL")
    private String productModel;

    /** 医保备注. */
    @Column(name = "PRODUCT_REMARK")
    @ApiModelProperty(value = "医保备注")
    private String productRemark;

    /** 申请机构编码. */
    @Column(name = "ORG_CODE")
    @ApiModelProperty(value = "申请机构编码")
    private String orgCode;

    /** 状态  0-禁用  1-启用. */
    @Column(name = "STATUS")
    @ApiModelProperty(value = "状态  0-禁用  1-启用")
    private String status;

    /** 删除标识 0-未删除 1-已删除. */
    @Column(name = "DELETE_FLAG")
    @ApiModelProperty(value = "删除标识 0-未删除 1-已删除")
    private String deleteFlag;
}