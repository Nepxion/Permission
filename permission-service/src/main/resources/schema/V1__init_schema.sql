/*==============================================================*/
/* Database: permission                                         */
/*==============================================================*/
create database if not exists permission default character set utf8 collate utf8_general_ci;

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
drop table if exists permission;

create table permission
(
   id                   int(16) unsigned not null auto_increment comment '主键',
   name                 varchar(64) binary not null comment '英文名',
   label                varchar(64) binary not null comment '中文名',
   type                 varchar(64) binary not null comment '类型',
   description          varchar(512) binary comment '描述',
   service_name         varchar(128) binary not null comment '服务名',
   resource             varchar(512) binary comment '权限所代表资源，例如要访问的URL',
   create_owner         varchar(64) binary not null comment '创建者',
   create_time          datetime not null default current_timestamp comment '创建时间',
   update_owner         varchar(64) binary not null comment '更新者',   
   update_time          datetime not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   primary key (id),
   unique index name (name, type, service_name)
)
engine = innodb
charset = utf8
collate = utf8_general_ci;

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
drop table if exists role;

create table role
(
   id                   int(16) unsigned not null auto_increment comment '主键',
   name                 varchar(64) binary not null comment '英文名',
   label                varchar(64) binary not null comment '中文名',
   description          varchar(512) binary comment '描述', 
   service_name         varchar(128) binary not null comment '服务名',
   create_owner         varchar(64) binary not null comment '创建者',
   create_time          datetime not null default current_timestamp comment '创建时间',
   update_owner         varchar(64) binary not null comment '更新者',   
   update_time          datetime not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',  
   primary key (id),
   unique index name (name, service_name)
)
engine = innodb
charset = utf8
collate = utf8_general_ci;

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
drop table if exists role_permission;

create table role_permission
(
   id                   int(16) unsigned not null auto_increment comment '主键',
   role_id              int(16) unsigned not null comment '角色ID',
   permission_id        int(16) unsigned not null comment '权限ID',
   name                 varchar(64) binary not null comment '英文名',
   label                varchar(64) binary not null comment '中文名',
   description          varchar(512) binary comment '描述',
   service_name         varchar(128) binary not null comment '服务名',
   create_owner         varchar(64) binary not null comment '创建者',
   create_time          datetime not null default current_timestamp comment '创建时间',
   update_owner         varchar(64) binary not null comment '更新者',   
   update_time          datetime not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间', 
   primary key (id),
   unique index id (role_id, permission_id),
   unique index name (name, service_name)
)
engine = innodb
charset = utf8
collate = utf8_general_ci;

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
drop table if exists user_role;

create table user_role
(
   id                   int(16) unsigned not null auto_increment comment '主键',
   user_id              char(36) binary not null comment '用户ID',
   user_type            char(36) binary not null comment '用户类型',   
   role_id              int(16) unsigned not null comment '角色ID',
   name                 varchar(64) binary not null comment '英文名',
   label                varchar(64) binary not null comment '中文名',
   description          varchar(512) binary comment '描述',
   service_name         varchar(128) binary not null comment '服务名',
   create_owner         varchar(64) binary not null comment '创建者',
   create_time          datetime not null default current_timestamp comment '创建时间',
   update_owner         varchar(64) binary not null comment '更新者',   
   update_time          datetime not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间', 
   primary key (id),
   unique index id (user_id, user_type, role_id),
   unique index name (name, service_name)
)
engine = innodb
charset = utf8
collate = utf8_general_ci;