package com.coding.distributed.tx.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping("/create")
    public Boolean create(@RequestParam Integer count) {
        Boolean result = orderService.create(count);
        log.info("result={}", result);
        return result;
    }
}
