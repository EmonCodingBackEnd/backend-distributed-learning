package com.coding.distributed.tx.order;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderService {

    @Autowired private RestTemplate restTemplate;

    @Autowired private ProductFeignClient productFeignClient;

    @GlobalTransactional
    public Boolean create(Integer count) {
        // 调用product扣库存
        // 方式1
        /*String url = "http://localhost:8086/deduct?productId=5001&count=" + count;
        Boolean result = restTemplate.getForObject(url, Boolean.TYPE);*/

        // 方式2
        Boolean result = productFeignClient.deduct(5001L, count);
        if (result != null && result) {
            // 可能抛出异常
            if (count == 5) {
                throw new RuntimeException("order发送异常了");
            }
            log.info("数据库开始创建订单！");
            return true;
        }
        return false;
    }
}
