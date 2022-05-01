package com.coding.distributed.sharding;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {
    @Id
    // sharding-readwrite-splitting-table-jpa、sharding-db-table-jpa和sharding-table-jpa会使用sharding配置的snowflake，需要打开如下配置
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // jpa和sharding-readwrite-splitting-jpa情况下，打开下面的注释；
    /*@GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
            name = "idGenerator",
            strategy = "com.coding.distributed.sharding.generator.SnowFlakeIdJpaGenerator")*/
    private Long orderId;

    private Integer userId;
}
