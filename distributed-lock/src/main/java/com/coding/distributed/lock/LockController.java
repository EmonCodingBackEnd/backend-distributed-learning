package com.coding.distributed.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class LockController {

    @Autowired private RedissonClient redissonClient;

    @GetMapping("/lock")
    public void lock() {
        RLock lock = redissonClient.getLock("lock");
        try {
            boolean b = lock.tryLock();
            if (b) {
                log.info("开始下单......");
                TimeUnit.SECONDS.sleep(3);
            } else {
                log.info("很遗憾......");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("解锁了");
            } else {
                log.info("不需要解锁");
            }
        }
    }
}
