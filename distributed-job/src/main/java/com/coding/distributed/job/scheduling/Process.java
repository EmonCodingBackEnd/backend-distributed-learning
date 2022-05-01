package com.coding.distributed.job.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Process {

    // 慎用，推荐MQ
    @Async
    public void processAsync() throws InterruptedException {
        log.info("processAsync......start");
        TimeUnit.SECONDS.sleep(5);
        log.info("processAsync......end");
    }
}
