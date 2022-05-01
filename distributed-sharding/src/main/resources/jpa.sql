-- 创建数据库
CREATE DATABASE IF NOT EXISTS ds0 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table t_order
(
    order_id bigint(20) unsigned not null,
    user_id  int(11) default null,
    primary key (order_id)
) engine = InnoDB
  default charset = utf8mb4;