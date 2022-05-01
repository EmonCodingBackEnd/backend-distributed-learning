package com.coding.distributed.tx.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping("/deduct")
    public Boolean deduct(@RequestParam Long productId, @RequestParam Integer count) {
        Boolean result = productService.deduct(productId, count);
        log.info("result={}", result);
        return result;
    }
}
