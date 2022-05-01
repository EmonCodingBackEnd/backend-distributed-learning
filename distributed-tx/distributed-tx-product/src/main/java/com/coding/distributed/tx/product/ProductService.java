package com.coding.distributed.tx.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductService {
    @Autowired private ProductDao productDao;

    public Boolean deduct(Long productId, Integer count) {
        Optional<ProductEntity> byId = productDao.findById(productId);
        if (byId.isPresent()) {
            log.info("开始扣库存. productId={},count={}", productId, count);
            ProductEntity entity = byId.get();
            entity.setCount(entity.getCount() - count);
            productDao.save(entity);
            return true;
        }
        return false;
    }
}
