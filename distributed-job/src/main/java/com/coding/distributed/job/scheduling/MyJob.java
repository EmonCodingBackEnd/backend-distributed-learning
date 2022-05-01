package com.coding.distributed.job.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyJob {

    /*
    fixedRate-固定速率，时间间隔是前次任务和下次任务的开始
    fixedDelay-固定延迟，时间间隔是前次任务的结束到下次任务的开始
    initialDelay-延迟启动
     */
    // @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    //    @Scheduled(cron = "0 0 0-18 * * 1/5")
    public void process() throws InterruptedException {
        log.info("process......start");
        //        TimeUnit.SECONDS.sleep(2);
        //        log.info("process......stop");
    }

    // ============================== 华丽的分割线 ==============================

    //    @Scheduled(fixedRate = 3000)
    public void process1() throws InterruptedException {
        log.info("process1......start");
        TimeUnit.SECONDS.sleep(5);
        log.info("process1......end");
    }

    //    @Scheduled(fixedRate = 3000)
    public void process2() throws InterruptedException {
        log.info("process2......start");
        TimeUnit.SECONDS.sleep(5);
        log.info("process2......end");
    }

    // ============================== 华丽的分割线 ==============================

    @Autowired
    Process process;

    //    @Scheduled(fixedRate = 3000)
    public void doProcessAsync() throws InterruptedException {
        process.processAsync();
    }
}
