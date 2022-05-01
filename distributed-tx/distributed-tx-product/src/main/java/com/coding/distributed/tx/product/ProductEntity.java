package com.coding.distributed.tx.product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Integer count;
}
