package com.coding.distributed.limiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LimiterController {

    private final RateLimiter rateLimiter = RateLimiter.create(10);

    @GetMapping("/guava")
    public void guava() {
        /*// 创建可放2个令牌的桶且每秒放2个令牌，0.5秒放1个令牌
        RateLimiter rateLimiter = RateLimiter.create(2);

        // 平滑输出，允许突发流量
        log.info("{}", rateLimiter.acquire(3));
        log.info("{}", rateLimiter.acquire());
        log.info("{}", rateLimiter.acquire());
        log.info("{}", rateLimiter.acquire());*/

        boolean tryAcquire = rateLimiter.tryAcquire();
        if (tryAcquire) {
            // 扣库存，下单......
            log.info("恭喜你，抢到了！");
        } else {
            log.info("不好意思，你手慢了，没抢到！");
        }
    }

    @Autowired private LimiterService limiterService;

    @GetMapping("/sentinel")
    public String sentinel() {
        limiterService.process();
        limiterService.hello("sentinel");
        return "sentinel";
    }
}
