/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 11                       */
/* Created on:     2020/7/4 13:32:08                            */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_CO_ORDER_CON_ORDER_DA') then
    alter table order_content
       delete foreign key FK_ORDER_CO_ORDER_CON_ORDER_DA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_CO_ORDER_CON_SJ_DATA') then
    alter table order_content
       delete foreign key FK_ORDER_CO_ORDER_CON_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_CO_ORDER_CON_SP_DATA') then
    alter table order_content
       delete foreign key FK_ORDER_CO_ORDER_CON_SP_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_DA_RELATIONS_SJ_DATA') then
    alter table order_data
       delete foreign key FK_ORDER_DA_RELATIONS_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_DA_RELATIONS_QS_DATA') then
    alter table order_data
       delete foreign key FK_ORDER_DA_RELATIONS_QS_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_DA_RELATIONS_USER_DAT') then
    alter table order_data
       delete foreign key FK_ORDER_DA_RELATIONS_USER_DAT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_DA_RELATIONS_USER_ADD') then
    alter table order_data
       delete foreign key FK_ORDER_DA_RELATIONS_USER_ADD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_DA_RELATIONS_SJ_YOUHI') then
    alter table order_data
       delete foreign key FK_ORDER_DA_RELATIONS_SJ_YOUHI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ORDER_DA_RELATIONS_SJ_MANJI') then
    alter table order_data
       delete foreign key FK_ORDER_DA_RELATIONS_SJ_MANJI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_QS_BILL_QS_BILL_ORDER_DA') then
    alter table qs_bill
       delete foreign key FK_QS_BILL_QS_BILL_ORDER_DA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_QS_BILL_QS_BILL_QS_DATA') then
    alter table qs_bill
       delete foreign key FK_QS_BILL_QS_BILL_QS_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_QS_BILL_QS_BILL_SP_EVALU') then
    alter table qs_bill
       delete foreign key FK_QS_BILL_QS_BILL_SP_EVALU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SJ_MANJI_RELATIONS_SJ_DATA') then
    alter table sj_manjian
       delete foreign key FK_SJ_MANJI_RELATIONS_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SJ_YOUHI_RELATIONS_SJ_DATA') then
    alter table sj_youhiquan
       delete foreign key FK_SJ_YOUHI_RELATIONS_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SP_DATA_RELATIONS_SJ_DATA') then
    alter table sp_data
       delete foreign key FK_SP_DATA_RELATIONS_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SP_DATA_RELATIONS_SP_LEIBI') then
    alter table sp_data
       delete foreign key FK_SP_DATA_RELATIONS_SP_LEIBI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SP_EVALU_RELATIONS_SJ_DATA') then
    alter table sp_evaluate
       delete foreign key FK_SP_EVALU_RELATIONS_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SP_EVALU_RELATIONS_SP_DATA') then
    alter table sp_evaluate
       delete foreign key FK_SP_EVALU_RELATIONS_SP_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SP_EVALU_RELATIONS_USER_DAT') then
    alter table sp_evaluate
       delete foreign key FK_SP_EVALU_RELATIONS_USER_DAT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SP_LEIBI_RELATIONS_SJ_DATA') then
    alter table sp_leibie
       delete foreign key FK_SP_LEIBI_RELATIONS_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_ADD_RELATIONS_USER_DAT') then
    alter table user_address
       delete foreign key FK_USER_ADD_RELATIONS_USER_DAT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_YOU_USER_YOUH_SJ_DATA') then
    alter table user_youhuiquan_get
       delete foreign key FK_USER_YOU_USER_YOUH_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_YOU_USER_YOUH_SJ_YOUHI') then
    alter table user_youhuiquan_get
       delete foreign key FK_USER_YOU_USER_YOUH_SJ_YOUHI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_YOU_USER_YOUH_USER_DAT') then
    alter table user_youhuiquan_get
       delete foreign key FK_USER_YOU_USER_YOUH_USER_DAT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_YOU_USER_YOUH_SJ_DATA') then
    alter table user_youhuiquan_jding
       delete foreign key FK_USER_YOU_USER_YOUH_SJ_DATA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_YOU_USER_YOUH_SJ_YOUHI') then
    alter table user_youhuiquan_jding
       delete foreign key FK_USER_YOU_USER_YOUH_SJ_YOUHI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_YOU_USER_YOUH_USER_DAT') then
    alter table user_youhuiquan_jding
       delete foreign key FK_USER_YOU_USER_YOUH_USER_DAT
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='cmd_data_PK'
     and t.table_name='cmd_data'
) then
   drop index cmd_data.cmd_data_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='cmd_data'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table cmd_data
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='order_content_FK3'
     and t.table_name='order_content'
) then
   drop index order_content.order_content_FK3
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='order_content_FK2'
     and t.table_name='order_content'
) then
   drop index order_content.order_content_FK2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='order_content_FK'
     and t.table_name='order_content'
) then
   drop index order_content.order_content_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='order_content_PK'
     and t.table_name='order_content'
) then
   drop index order_content.order_content_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='order_content'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table order_content
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_15_FK'
     and t.table_name='order_data'
) then
   drop index order_data.Relationship_15_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_14_FK'
     and t.table_name='order_data'
) then
   drop index order_data.Relationship_14_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_13_FK'
     and t.table_name='order_data'
) then
   drop index order_data.Relationship_13_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_12_FK'
     and t.table_name='order_data'
) then
   drop index order_data.Relationship_12_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_11_FK'
     and t.table_name='order_data'
) then
   drop index order_data.Relationship_11_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_10_FK'
     and t.table_name='order_data'
) then
   drop index order_data.Relationship_10_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='order_data_PK'
     and t.table_name='order_data'
) then
   drop index order_data.order_data_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='order_data'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table order_data
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='qs_bill_FK3'
     and t.table_name='qs_bill'
) then
   drop index qs_bill.qs_bill_FK3
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='qs_bill_FK2'
     and t.table_name='qs_bill'
) then
   drop index qs_bill.qs_bill_FK2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='qs_bill_FK'
     and t.table_name='qs_bill'
) then
   drop index qs_bill.qs_bill_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='qs_bill_PK'
     and t.table_name='qs_bill'
) then
   drop index qs_bill.qs_bill_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='qs_bill'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table qs_bill
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='qs_data_PK'
     and t.table_name='qs_data'
) then
   drop index qs_data.qs_data_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='qs_data'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table qs_data
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='sj_data_PK'
     and t.table_name='sj_data'
) then
   drop index sj_data.sj_data_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='sj_data'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table sj_data
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_1_FK'
     and t.table_name='sj_manjian'
) then
   drop index sj_manjian.Relationship_1_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='sj_manjian_PK'
     and t.table_name='sj_manjian'
) then
   drop index sj_manjian.sj_manjian_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='sj_manjian'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table sj_manjian
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_2_FK'
     and t.table_name='sj_youhiquan'
) then
   drop index sj_youhiquan.Relationship_2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='sj_youhiquan_PK'
     and t.table_name='sj_youhiquan'
) then
   drop index sj_youhiquan.sj_youhiquan_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='sj_youhiquan'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table sj_youhiquan
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_5_FK'
     and t.table_name='sp_data'
) then
   drop index sp_data.Relationship_5_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_4_FK'
     and t.table_name='sp_data'
) then
   drop index sp_data.Relationship_4_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='sp_data_PK'
     and t.table_name='sp_data'
) then
   drop index sp_data.sp_data_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='sp_data'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table sp_data
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_8_FK'
     and t.table_name='sp_evaluate'
) then
   drop index sp_evaluate.Relationship_8_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_7_FK'
     and t.table_name='sp_evaluate'
) then
   drop index sp_evaluate.Relationship_7_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_6_FK'
     and t.table_name='sp_evaluate'
) then
   drop index sp_evaluate.Relationship_6_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='sp_evaluate_PK'
     and t.table_name='sp_evaluate'
) then
   drop index sp_evaluate.sp_evaluate_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='sp_evaluate'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table sp_evaluate
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_3_FK'
     and t.table_name='sp_leibie'
) then
   drop index sp_leibie.Relationship_3_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='sp_leibie_PK'
     and t.table_name='sp_leibie'
) then
   drop index sp_leibie.sp_leibie_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='sp_leibie'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table sp_leibie
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Relationship_9_FK'
     and t.table_name='user_address'
) then
   drop index user_address.Relationship_9_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_address_PK'
     and t.table_name='user_address'
) then
   drop index user_address.user_address_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='user_address'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table user_address
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_data_PK'
     and t.table_name='user_data'
) then
   drop index user_data.user_data_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='user_data'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table user_data
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_get_FK3'
     and t.table_name='user_youhuiquan_get'
) then
   drop index user_youhuiquan_get.user_youhuiquan_get_FK3
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_get_FK2'
     and t.table_name='user_youhuiquan_get'
) then
   drop index user_youhuiquan_get.user_youhuiquan_get_FK2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_get_FK'
     and t.table_name='user_youhuiquan_get'
) then
   drop index user_youhuiquan_get.user_youhuiquan_get_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_get_PK'
     and t.table_name='user_youhuiquan_get'
) then
   drop index user_youhuiquan_get.user_youhuiquan_get_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='user_youhuiquan_get'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table user_youhuiquan_get
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_jding_FK3'
     and t.table_name='user_youhuiquan_jding'
) then
   drop index user_youhuiquan_jding.user_youhuiquan_jding_FK3
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_jding_FK2'
     and t.table_name='user_youhuiquan_jding'
) then
   drop index user_youhuiquan_jding.user_youhuiquan_jding_FK2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_jding_FK'
     and t.table_name='user_youhuiquan_jding'
) then
   drop index user_youhuiquan_jding.user_youhuiquan_jding_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='user_youhuiquan_jding_PK'
     and t.table_name='user_youhuiquan_jding'
) then
   drop index user_youhuiquan_jding.user_youhuiquan_jding_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='user_youhuiquan_jding'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table user_youhuiquan_jding
end if;

/*==============================================================*/
/* Table: cmd_data                                              */
/*==============================================================*/
create table cmd_data 
(
   cmd_id               integer                        not null,
   cmd_name             varchar(20)                    not null,
   cmd_pwd              varchar(20)                    not null,
   constraint PK_CMD_DATA primary key (cmd_id)
);

/*==============================================================*/
/* Index: cmd_data_PK                                           */
/*==============================================================*/
create unique index cmd_data_PK on cmd_data (
cmd_id ASC
);

/*==============================================================*/
/* Table: order_content                                         */
/*==============================================================*/
create table order_content 
(
   sj_id                integer                        not null,
   sp_id                integer                        not null,
   order_id             integer                        not null,
   order_count          integer                        not null,
   order_one_money      float(10)                      not null,
   order_one_discount   float(10)                      not null,
   constraint PK_ORDER_CONTENT primary key clustered (sj_id, sp_id, order_id)
);

/*==============================================================*/
/* Index: order_content_PK                                      */
/*==============================================================*/
create unique clustered index order_content_PK on order_content (
sj_id ASC,
sp_id ASC,
order_id ASC
);

/*==============================================================*/
/* Index: order_content_FK                                      */
/*==============================================================*/
create index order_content_FK on order_content (
sj_id ASC
);

/*==============================================================*/
/* Index: order_content_FK2                                     */
/*==============================================================*/
create index order_content_FK2 on order_content (
sp_id ASC
);

/*==============================================================*/
/* Index: order_content_FK3                                     */
/*==============================================================*/
create index order_content_FK3 on order_content (
order_id ASC
);

/*==============================================================*/
/* Table: order_data                                            */
/*==============================================================*/
create table order_data 
(
   order_id             integer                        not null,
   user_address_id      integer                        null,
   mj_id                integer                        null,
   qs_id                integer                        null,
   sj_id                integer                        null,
   youhuiquan_id        Integer(10)                    null,
   user_id              integer                        null,
   order_origin_money   float(10)                      not null,
   order_final_money    float(10)                      not null,
   order_set_time       timestamp                      not null,
   order_set_arrive_time timestamp                      not null,
   order_state          varchar(20)                    not null,
   constraint PK_ORDER_DATA primary key (order_id)
);

/*==============================================================*/
/* Index: order_data_PK                                         */
/*==============================================================*/
create unique index order_data_PK on order_data (
order_id ASC
);

/*==============================================================*/
/* Index: Relationship_10_FK                                    */
/*==============================================================*/
create index Relationship_10_FK on order_data (
sj_id ASC
);

/*==============================================================*/
/* Index: Relationship_11_FK                                    */
/*==============================================================*/
create index Relationship_11_FK on order_data (
qs_id ASC
);

/*==============================================================*/
/* Index: Relationship_12_FK                                    */
/*==============================================================*/
create index Relationship_12_FK on order_data (
user_id ASC
);

/*==============================================================*/
/* Index: Relationship_13_FK                                    */
/*==============================================================*/
create index Relationship_13_FK on order_data (
user_address_id ASC
);

/*==============================================================*/
/* Index: Relationship_14_FK                                    */
/*==============================================================*/
create index Relationship_14_FK on order_data (
youhuiquan_id ASC
);

/*==============================================================*/
/* Index: Relationship_15_FK                                    */
/*==============================================================*/
create index Relationship_15_FK on order_data (
mj_id ASC
);

/*==============================================================*/
/* Table: qs_bill                                               */
/*==============================================================*/
create table qs_bill 
(
   qs_id                integer                        not null,
   sp_evaluate_qsxinji  integer                        not null,
   order_id             integer                        not null,
   qs_getmoney_time     timestamp                      not null,
   qs_getmoney          float(10)                      not null,
   constraint PK_QS_BILL primary key clustered (qs_id, sp_evaluate_qsxinji, order_id)
);

/*==============================================================*/
/* Index: qs_bill_PK                                            */
/*==============================================================*/
create unique clustered index qs_bill_PK on qs_bill (
qs_id ASC,
sp_evaluate_qsxinji ASC,
order_id ASC
);

/*==============================================================*/
/* Index: qs_bill_FK                                            */
/*==============================================================*/
create index qs_bill_FK on qs_bill (
qs_id ASC
);

/*==============================================================*/
/* Index: qs_bill_FK2                                           */
/*==============================================================*/
create index qs_bill_FK2 on qs_bill (
sp_evaluate_qsxinji ASC
);

/*==============================================================*/
/* Index: qs_bill_FK3                                           */
/*==============================================================*/
create index qs_bill_FK3 on qs_bill (
order_id ASC
);

/*==============================================================*/
/* Table: qs_data                                               */
/*==============================================================*/
create table qs_data 
(
   qs_id                integer                        not null,
   qs_name              varchar(20)                    not null,
   qs_join_date         timestamp                      not null,
   qs_grade             varchar(20)                    not null,
   constraint PK_QS_DATA primary key (qs_id)
);

/*==============================================================*/
/* Index: qs_data_PK                                            */
/*==============================================================*/
create unique index qs_data_PK on qs_data (
qs_id ASC
);

/*==============================================================*/
/* Table: sj_data                                               */
/*==============================================================*/
create table sj_data 
(
   sj_id                integer                        not null,
   sj_name              varchar(20)                    not null,
   sj_xinji             integer                        not null,
   avg_consume          float(10)                      null,
   total_consume        float(20)                      null,
   constraint PK_SJ_DATA primary key (sj_id)
);

/*==============================================================*/
/* Index: sj_data_PK                                            */
/*==============================================================*/
create unique index sj_data_PK on sj_data (
sj_id ASC
);

/*==============================================================*/
/* Table: sj_manjian                                            */
/*==============================================================*/
create table sj_manjian 
(
   mj_id                integer                        not null,
   sj_id                integer                        null,
   mj_top_money         float(10)                      not null,
   mj_count_money       float(10)                      not null,
   if_add_youhuiquan    binary(1)                      not null,
   constraint PK_SJ_MANJIAN primary key (mj_id)
);

/*==============================================================*/
/* Index: sj_manjian_PK                                         */
/*==============================================================*/
create unique index sj_manjian_PK on sj_manjian (
mj_id ASC
);

/*==============================================================*/
/* Index: Relationship_1_FK                                     */
/*==============================================================*/
create index Relationship_1_FK on sj_manjian (
sj_id ASC
);

/*==============================================================*/
/* Table: sj_youhiquan                                          */
/*==============================================================*/
create table sj_youhiquan 
(
   youhuiquan_id        Integer(10)                    not null,
   sj_id                integer                        null,
   youhui_money         float(10)                      not null,
   jidan_least_count    Integer(10)                    not null,
   youhuiquan_begin_time timestamp                      not null,
   youhuiquan_end_time  timestamp                      not null,
   constraint PK_SJ_YOUHIQUAN primary key (youhuiquan_id)
);

/*==============================================================*/
/* Index: sj_youhiquan_PK                                       */
/*==============================================================*/
create unique index sj_youhiquan_PK on sj_youhiquan (
youhuiquan_id ASC
);

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on sj_youhiquan (
sj_id ASC
);

/*==============================================================*/
/* Table: sp_data                                               */
/*==============================================================*/
create table sp_data 
(
   sp_id                integer                        not null,
   leibie_id            integer                        null,
   sj_id                integer                        null,
   sp_name              varchar(50)                    not null,
   sp_price             float(10)                      not null,
   sp_final_price       float(10)                      null,
   constraint PK_SP_DATA primary key (sp_id)
);

/*==============================================================*/
/* Index: sp_data_PK                                            */
/*==============================================================*/
create unique index sp_data_PK on sp_data (
sp_id ASC
);

/*==============================================================*/
/* Index: Relationship_4_FK                                     */
/*==============================================================*/
create index Relationship_4_FK on sp_data (
sj_id ASC
);

/*==============================================================*/
/* Index: Relationship_5_FK                                     */
/*==============================================================*/
create index Relationship_5_FK on sp_data (
leibie_id ASC
);

/*==============================================================*/
/* Table: sp_evaluate                                           */
/*==============================================================*/
create table sp_evaluate 
(
   sp_evaluate_content  varchar(100)                   not null,
   sp_evaluate_time     timestamp                      not null,
   sp_evaluate_spxinji  integer                        not null,
   sp_evaluate_qsxinji  integer                        not null,
   user_id              integer                        null,
   sp_id                integer                        null,
   sj_id                integer                        null,
   constraint PK_SP_EVALUATE primary key (sp_evaluate_qsxinji)
);

/*==============================================================*/
/* Index: sp_evaluate_PK                                        */
/*==============================================================*/
create unique index sp_evaluate_PK on sp_evaluate (
sp_evaluate_qsxinji ASC
);

/*==============================================================*/
/* Index: Relationship_6_FK                                     */
/*==============================================================*/
create index Relationship_6_FK on sp_evaluate (
sj_id ASC
);

/*==============================================================*/
/* Index: Relationship_7_FK                                     */
/*==============================================================*/
create index Relationship_7_FK on sp_evaluate (
sp_id ASC
);

/*==============================================================*/
/* Index: Relationship_8_FK                                     */
/*==============================================================*/
create index Relationship_8_FK on sp_evaluate (
user_id ASC
);

/*==============================================================*/
/* Table: sp_leibie                                             */
/*==============================================================*/
create table sp_leibie 
(
   leibie_id            integer                        not null,
   sj_id                integer                        null,
   leibie_name          varchar(50)                    not null,
   sp_count             integer                        not null,
   constraint PK_SP_LEIBIE primary key (leibie_id)
);

/*==============================================================*/
/* Index: sp_leibie_PK                                          */
/*==============================================================*/
create unique index sp_leibie_PK on sp_leibie (
leibie_id ASC
);

/*==============================================================*/
/* Index: Relationship_3_FK                                     */
/*==============================================================*/
create index Relationship_3_FK on sp_leibie (
sj_id ASC
);

/*==============================================================*/
/* Table: user_address                                          */
/*==============================================================*/
create table user_address 
(
   user_address_id      integer                        not null,
   user_id              integer                        null,
   user_province        varchar(20)                    not null,
   user_city            varchar(20)                    not null,
   user_area            varchar(20)                    not null,
   user_address_detail  varchar(40)                    not null,
   user_ad_name         varchar(20)                    not null,
   user_ad_phonenum     varchar(12)                    not null,
   constraint PK_USER_ADDRESS primary key (user_address_id)
);

/*==============================================================*/
/* Index: user_address_PK                                       */
/*==============================================================*/
create unique index user_address_PK on user_address (
user_address_id ASC
);

/*==============================================================*/
/* Index: Relationship_9_FK                                     */
/*==============================================================*/
create index Relationship_9_FK on user_address (
user_id ASC
);

/*==============================================================*/
/* Table: user_data                                             */
/*==============================================================*/
create table user_data 
(
   user_id              integer                        not null,
   user_name            varchar(20)                    not null,
   user_sex             integer                        not null,
   user_pwd             varchar(20)                    not null,
   user_phonenum        varchar(12)                    not null,
   user_email           varchar(40)                    not null,
   user_city            varchar(20)                    not null,
   user_register_time   timestamp                      not null,
   user_vip_end_time    timestamp                      not null,
   constraint PK_USER_DATA primary key (user_id)
);

/*==============================================================*/
/* Index: user_data_PK                                          */
/*==============================================================*/
create unique index user_data_PK on user_data (
user_id ASC
);

/*==============================================================*/
/* Table: user_youhuiquan_get                                   */
/*==============================================================*/
create table user_youhuiquan_get 
(
   user_id              integer                        not null,
   sj_id                integer                        not null,
   youhuiquan_id        Integer(10)                    not null,
   youhui_money         float(10)                      not null,
   youhuiquan_count     integer                        not null,
   youhuiquan_end_time  timestamp                      not null,
   constraint PK_USER_YOUHUIQUAN_GET primary key clustered (user_id, sj_id, youhuiquan_id)
);

/*==============================================================*/
/* Index: user_youhuiquan_get_PK                                */
/*==============================================================*/
create unique clustered index user_youhuiquan_get_PK on user_youhuiquan_get (
user_id ASC,
sj_id ASC,
youhuiquan_id ASC
);

/*==============================================================*/
/* Index: user_youhuiquan_get_FK                                */
/*==============================================================*/
create index user_youhuiquan_get_FK on user_youhuiquan_get (
user_id ASC
);

/*==============================================================*/
/* Index: user_youhuiquan_get_FK2                               */
/*==============================================================*/
create index user_youhuiquan_get_FK2 on user_youhuiquan_get (
sj_id ASC
);

/*==============================================================*/
/* Index: user_youhuiquan_get_FK3                               */
/*==============================================================*/
create index user_youhuiquan_get_FK3 on user_youhuiquan_get (
youhuiquan_id ASC
);

/*==============================================================*/
/* Table: user_youhuiquan_jding                                 */
/*==============================================================*/
create table user_youhuiquan_jding 
(
   user_id              integer                        not null,
   youhuiquan_id        Integer(10)                    not null,
   sj_id                integer                        not null,
   jidan_least_count    integer                        not null,
   order_count          integer                        not null,
   constraint PK_USER_YOUHUIQUAN_JDING primary key clustered (user_id, youhuiquan_id, sj_id)
);

/*==============================================================*/
/* Index: user_youhuiquan_jding_PK                              */
/*==============================================================*/
create unique clustered index user_youhuiquan_jding_PK on user_youhuiquan_jding (
user_id ASC,
youhuiquan_id ASC,
sj_id ASC
);

/*==============================================================*/
/* Index: user_youhuiquan_jding_FK                              */
/*==============================================================*/
create index user_youhuiquan_jding_FK on user_youhuiquan_jding (
user_id ASC
);

/*==============================================================*/
/* Index: user_youhuiquan_jding_FK2                             */
/*==============================================================*/
create index user_youhuiquan_jding_FK2 on user_youhuiquan_jding (
youhuiquan_id ASC
);

/*==============================================================*/
/* Index: user_youhuiquan_jding_FK3                             */
/*==============================================================*/
create index user_youhuiquan_jding_FK3 on user_youhuiquan_jding (
sj_id ASC
);

alter table order_content
   add constraint FK_ORDER_CO_ORDER_CON_ORDER_DA foreign key (order_id)
      references order_data (order_id)
      on update restrict
      on delete restrict;

alter table order_content
   add constraint FK_ORDER_CO_ORDER_CON_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table order_content
   add constraint FK_ORDER_CO_ORDER_CON_SP_DATA foreign key (sp_id)
      references sp_data (sp_id)
      on update restrict
      on delete restrict;

alter table order_data
   add constraint FK_ORDER_DA_RELATIONS_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table order_data
   add constraint FK_ORDER_DA_RELATIONS_QS_DATA foreign key (qs_id)
      references qs_data (qs_id)
      on update restrict
      on delete restrict;

alter table order_data
   add constraint FK_ORDER_DA_RELATIONS_USER_DAT foreign key (user_id)
      references user_data (user_id)
      on update restrict
      on delete restrict;

alter table order_data
   add constraint FK_ORDER_DA_RELATIONS_USER_ADD foreign key (user_address_id)
      references user_address (user_address_id)
      on update restrict
      on delete restrict;

alter table order_data
   add constraint FK_ORDER_DA_RELATIONS_SJ_YOUHI foreign key (youhuiquan_id)
      references sj_youhiquan (youhuiquan_id)
      on update restrict
      on delete restrict;

alter table order_data
   add constraint FK_ORDER_DA_RELATIONS_SJ_MANJI foreign key (mj_id)
      references sj_manjian (mj_id)
      on update restrict
      on delete restrict;

alter table qs_bill
   add constraint FK_QS_BILL_QS_BILL_ORDER_DA foreign key (order_id)
      references order_data (order_id)
      on update restrict
      on delete restrict;

alter table qs_bill
   add constraint FK_QS_BILL_QS_BILL_QS_DATA foreign key (qs_id)
      references qs_data (qs_id)
      on update restrict
      on delete restrict;

alter table qs_bill
   add constraint FK_QS_BILL_QS_BILL_SP_EVALU foreign key (sp_evaluate_qsxinji)
      references sp_evaluate (sp_evaluate_qsxinji)
      on update restrict
      on delete restrict;

alter table sj_manjian
   add constraint FK_SJ_MANJI_RELATIONS_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table sj_youhiquan
   add constraint FK_SJ_YOUHI_RELATIONS_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table sp_data
   add constraint FK_SP_DATA_RELATIONS_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table sp_data
   add constraint FK_SP_DATA_RELATIONS_SP_LEIBI foreign key (leibie_id)
      references sp_leibie (leibie_id)
      on update restrict
      on delete restrict;

alter table sp_evaluate
   add constraint FK_SP_EVALU_RELATIONS_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table sp_evaluate
   add constraint FK_SP_EVALU_RELATIONS_SP_DATA foreign key (sp_id)
      references sp_data (sp_id)
      on update restrict
      on delete restrict;

alter table sp_evaluate
   add constraint FK_SP_EVALU_RELATIONS_USER_DAT foreign key (user_id)
      references user_data (user_id)
      on update restrict
      on delete restrict;

alter table sp_leibie
   add constraint FK_SP_LEIBI_RELATIONS_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table user_address
   add constraint FK_USER_ADD_RELATIONS_USER_DAT foreign key (user_id)
      references user_data (user_id)
      on update restrict
      on delete restrict;

alter table user_youhuiquan_get
   add constraint FK_USER_YOU_USER_YOUH_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table user_youhuiquan_get
   add constraint FK_USER_YOU_USER_YOUH_SJ_YOUHI foreign key (youhuiquan_id)
      references sj_youhiquan (youhuiquan_id)
      on update restrict
      on delete restrict;

alter table user_youhuiquan_get
   add constraint FK_USER_YOU_USER_YOUH_USER_DAT foreign key (user_id)
      references user_data (user_id)
      on update restrict
      on delete restrict;

alter table user_youhuiquan_jding
   add constraint FK_USER_YOU_USER_YOUH_SJ_DATA foreign key (sj_id)
      references sj_data (sj_id)
      on update restrict
      on delete restrict;

alter table user_youhuiquan_jding
   add constraint FK_USER_YOU_USER_YOUH_SJ_YOUHI foreign key (youhuiquan_id)
      references sj_youhiquan (youhuiquan_id)
      on update restrict
      on delete restrict;

alter table user_youhuiquan_jding
   add constraint FK_USER_YOU_USER_YOUH_USER_DAT foreign key (user_id)
      references user_data (user_id)
      on update restrict
      on delete restrict;

