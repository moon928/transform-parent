/*==============================================================*/
/* Table: USC_DICT_CATALOG                                      */
/*==============================================================*/
create table TMP_YJJ_DATA
(
    ID                   bigint(20) not null comment '主键ID',
    CHEMICAL_NAME        varchar(100)  comment '产品名称',
    ENG_NAME             varchar(100)  comment '产品英文名称',
    DRUG_DOSFORM_CODE    varchar(50)  comment '药品剂型 字典：DOSFORM',
    DRUG_TYPE            varchar(50)  comment '药品类型 字典：drug_type',
    SPEC_DESC            varchar(100)  comment '注册规格',
    VERSION_ID           bigint(20) not null comment '版本id',
    COMNAME              varchar(100)  comment '商品名称',
    DRUGCODE_SPDA        varchar(20)  comment '药品本位码',
    APP_CODE             varchar(50)  comment '批准文号',
    FACT_NAME            varchar(100)  comment '生产厂家名称',
    REL_ADDR             varchar(100)  comment '联系地址',
    DOMESTIC int(11) NULL DEFAULT NULL COMMENT '是否国产 0-否 1-是',
    IMPORTED int(11) NULL DEFAULT NULL COMMENT '是否进口 0-否 1-是',

    CREATE_BY bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    CREATE_TIME timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UPDATE_BY bigint(20) NULL DEFAULT NULL COMMENT '最后修改人',
    UPDATE_TIME timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
   primary key (ID)
);

alter table TMP_YJJ_DATA comment '药监局数据临时表';
