-- 创建数据库
CREATE DATABASE IF NOT EXISTS db_product DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use db_product;

CREATE TABLE `t_product`
(
    `product_id` bigint(20) unsigned NOT NULL,
    `count`      int(11) DEFAULT NULL,
    PRIMARY KEY (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into t_product (product_id, count)
values (5001, 100);


-- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;