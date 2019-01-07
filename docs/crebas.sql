/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/4 16:50:32                            */
/*==============================================================*/


alter table t_express_company
   drop primary key;

drop table if exists t_express_company;

alter table t_goods
   drop primary key;

drop table if exists t_goods;

alter table t_goods_repertory
   drop primary key;

drop table if exists t_goods_repertory;

alter table t_in_repo
   drop primary key;

drop table if exists t_in_repo;

alter table t_in_repo_items
   drop primary key;

drop table if exists t_in_repo_items;

alter table t_out_repo
   drop primary key;

drop table if exists t_out_repo;

alter table t_out_repo_items
   drop primary key;

drop table if exists t_out_repo_items;

alter table t_repertory_regulation
   drop primary key;

drop table if exists t_repertory_regulation;

alter table t_user
   drop primary key;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_express_company                                     */
/*==============================================================*/
create table t_express_company
(
   id                   int not null auto_increment,
   express_company_name varchar(30),
   contact_way          varchar(30)
);

alter table t_express_company
   add primary key (id);

/*==============================================================*/
/* Table: t_goods                                               */
/*==============================================================*/
create table t_goods
(
   id                   int not null auto_increment,
   sku                  varchar(30),
   goods_name           varchar(30),
   goods_price          float
);

alter table t_goods
   add primary key (id);

/*==============================================================*/
/* Table: t_goods_repertory                                     */
/*==============================================================*/
create table t_goods_repertory
(
   id                   int not null auto_increment,
   goods_id             int,
   total_num            int,
   available_num        int,
   locked_num           int
);

alter table t_goods_repertory
   add primary key (id);

/*==============================================================*/
/* Table: t_in_repo                                             */
/*==============================================================*/
create table t_in_repo
(
   id                   int not null auto_increment,
   in_repo_id           varchar(30),
   order_id             varchar(30),
   channel_id           varchar(30),
   express_id           varchar(30),
   express_company      varchar(30),
   in_repo_status       varchar(30),
   sync_status          varchar(30),
   receiving_repo       varchar(30),
   create_time          datetime,
   reviser              varchar(30),
   revise_time          datetime
);

alter table t_in_repo
   add primary key (id);

/*==============================================================*/
/* Table: t_in_repo_items                                       */
/*==============================================================*/
create table t_in_repo_items
(
   id                   int not null auto_increment,
   in_repo_id           int,
   goods_id             int,
   goods_name           varchar(30),
   goods_num            int
);

alter table t_in_repo_items
   add primary key (id);

/*==============================================================*/
/* Table: t_out_repo                                            */
/*==============================================================*/
create table t_out_repo
(
   id                   int not null auto_increment,
   out_repo_id          varchar(30),
   order_id             varchar(30),
   channel_id           varchar(30),
   receiver_name        varchar(30),
   receiver_address     varchar(30),
   express_id           varchar(30),
   express_company      varchar(30),
   out_repo_status      varchar(30),
   sync_status          varchar(30),
   create_time          datetime,
   reviser              varchar(30),
   revise_time          datetime
);

alter table t_out_repo
   add primary key (id);

/*==============================================================*/
/* Table: t_out_repo_items                                      */
/*==============================================================*/
create table t_out_repo_items
(
   id                   int not null auto_increment,
   out_repo_id          int,
   goods_id             int,
   goods_name           varchar(30),
   goods_num            int
);

alter table t_out_repo_items
   add primary key (id);

/*==============================================================*/
/* Table: t_repertory_regulation                                */
/*==============================================================*/
create table t_repertory_regulation
(
   id                   int not null auto_increment,
   goods_repertory_id   int,
   action               int
);

alter table t_repertory_regulation
   add primary key (id);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   int not null auto_increment,
   user_name            varchar(30),
   password             varchar(100)
);

alter table t_user
   add primary key (id);

