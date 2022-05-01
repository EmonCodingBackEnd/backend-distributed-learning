package com.coding.distributed.job.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyXxlJob {

    /*@XxlJob("myXxlJobHandler")
    public void myXxlJobHandler() throws Exception {
        // 轮询 流量均摊 推荐
        // 故障转义 流量到第一台，查询日志更方便

        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer i : list) {
            if (i % shardTotal == shardIndex) {
                log.info(
                        "myXxlJobHandler execute...user={}. shardIndex={}, shardTotal={}",
                        i,
                        shardIndex,
                        shardTotal);
            }
        }

        XxlJobHelper.log("XXL-JOB, Hello World.");
    }*/

    @XxlJob("myXxlJobHandler")
    public void myXxlJobHandler() throws Exception {
        log.info("myXxlJobHandler execute...");
        TimeUnit.SECONDS.sleep(3);

        XxlJobHelper.log("XXL-JOB, Hello World.");
    }
}
