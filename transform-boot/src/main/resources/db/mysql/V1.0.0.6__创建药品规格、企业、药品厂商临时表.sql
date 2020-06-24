
/*==============================================================*/
/* Table: USC_DRG_SPECS                                         */
/*==============================================================*/
create table TMP_SPECS
(
   ID                   bigint(20) not null comment '主键ID',
   STANDARD_CODE        varchar(50) not null comment '药品编码',
   CHEMICAL_NAME        varchar(100) not null comment '注册名称',
   DRUG_DOSFORM_CODE    varchar(50) not null comment '药品剂型 字典：DOSFORM',
   DRUG_TYPE            varchar(50) comment '药品类型 字典：drug_type',
   ENG_NANE             varchar(100) comment '英文名称',
   PY_CODE              varchar(100) comment '拼音码',
   WB_CODE              varchar(100) comment '五笔码',
   SPEC_DESC            varchar(100) comment '注册规格',
   DOSE                 double comment '剂量',
   DOSEUNIT_CODE        varchar(50)  comment '剂量单位 字典:YPDW_001',
   CONTENT              double  comment '含量',
   CONTENTUNIT_CODE     varchar(50)  comment '含量单位 字典:YPDW_001',
   PACKNUM              int  comment '包装数量',
   PACKUNIT_CODE        varchar(50)  comment '包装单位 字典:YPDW_001',
   PACK_MATERIAL        varchar(100) comment '包装材质 字典:PACK_MATERIAL',
   UNIT                 varchar(50)  comment '单位 字典:YPDW_001',
   ANTI_GRADE_CODE      varchar(50) comment '抗生素等级 字典:pub_level',
   DDD_VALUE            int default 0 comment 'DDD值',
   DDD_DOSE             int comment 'DDD剂量',
   MEMO                 varchar(400) comment '备注',
   DOMESTIC             int comment '是否国产 0-否 1-是',
   IMPORTED             int comment '是否进口 0-否 1-是',
   VERSION_ID           bigint(20) not null comment '版本id',
   QUOTE_DRG_ID         bigint(20) comment '引用药品ID',
   PRODUCT_INS_TYPE     varchar(32) comment '甲乙类  字典：PRODUCT_INS_TYPE',
   PRODUCT_CODE         varchar(50) comment '医保编号',
   PRODUCT_NAME         varchar(50) comment '医保药品名称',
   PRODUCT_MODEL        varchar(32) comment '医保剂型 字典:PRODUCT_MODEL',
   PRODUCT_REMARK       varchar(255) comment '医保备注',
   ORG_CODE             varchar(25) comment '申请机构编码',
   STATUS               char(1) not null default '1' comment '状态  0-禁用  1-启用',
   DELETE_FLAG          char(1) not null default '0' comment '删除标识 0-未删除 1-已删除',
   CREATE_BY            bigint(20) comment '创建人',
   CREATE_TIME          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   UPDATE_BY            bigint(20) comment '最后修改人',
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP comment '最后修改时间',
   primary key (ID)
);

alter table TMP_SPECS comment '临时的 药品规格表';


/*==============================================================*/
/* Table: USC_DRG_DICTIONARY                                    */
/*==============================================================*/
create table TMP_DICTIONARY
(
   ID                   bigint(20) not null comment '主键ID',
   SPECID               varchar(32) not null comment '药品规格ID',
   COMNAME              varchar(100) comment '商品名',
   DRUGCODE_SPDA        varchar(20) comment '药品本位码',
   DRUGCODE_HPC         varchar(50) comment '药品代码',
   APP_CODE             varchar(50) comment '批准文号',
   FACT_ID              bigint(20) not null comment '生产厂家ID',
   MEMO                 varchar(100) comment '备注',
   PUBLISH              char(1) default '0' comment '是否发布  0-未发布 1-已发布',
   STATUS               char(1) not null default '1' comment '状态  0-禁用  1-启用',
   DELETE_FLAG          char(1) not null default '0' comment '删除标识 0-未删除 1-已删除',
   CREATE_BY            bigint(20) comment '创建人',
   CREATE_TIME          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   UPDATE_BY            bigint(20) comment '最后修改人',
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP comment '最后修改时间',
   primary key (ID)
);

alter table TMP_DICTIONARY comment '临时的 药品厂家表';


/*==============================================================*/
/* Table: USC_DRG_FACTORY                                       */
/*==============================================================*/
create table TMP_FACTORY
(
   ID                   bigint(20) not null comment '主键ID',
   FACT_NAME            varchar(100) not null comment '生产厂家名称',
   WB_CODE              varchar(100) comment '五笔码',
   PY_CODE              varchar(100) comment '拼音码',
   REL_TEL              varchar(20) comment '联系电话',
   REL_MAN              varchar(30) comment '联系人',
   REL_ADDR             varchar(300) comment '联系地址',
   ISGMP                int comment '是否GMP达标',
   PUBLISH              char(1) not null default '0' comment '是否发布 0-未发布 1-已发布',
   STATUS               char(1) not null default '1' comment '状态  0-禁用  1-启用',
   DELETE_FLAG          char(1) not null default '0' comment '删除标识 0-未删除 1-已删除',
   CREATE_BY            bigint(20) comment '创建人',
   CREATE_TIME          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   UPDATE_BY            bigint(20) comment '最后修改人',
   UPDATE_TIME          timestamp default CURRENT_TIMESTAMP comment '最后修改时间',
   primary key (ID)
);

alter table TMP_FACTORY comment '临时的 药品生产厂家';


