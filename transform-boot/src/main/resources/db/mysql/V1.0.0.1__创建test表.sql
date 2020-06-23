/*==============================================================*/
/* Table: USC_DICT_CATALOG                                      */
/*==============================================================*/
create table T_TEST
(
   ID                   bigint(20) not null comment '主键ID',
   NAME                 varchar(100) not null comment '中文名称',
   WB_CODE              varchar(60) comment '五笔码',
   PY_CODE              varchar(60) comment '拼音码',
   primary key (ID)
);

alter table T_TEST comment '中文转拼音与五笔码测试表';
