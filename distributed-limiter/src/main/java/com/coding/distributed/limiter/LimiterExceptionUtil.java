package com.coding.distributed.limiter;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class LimiterExceptionUtil {

    public static String handleException(BlockException ex) {
        log.info("Oops: " + ex.getClass().getCanonicalName());
        return "Oops: " + ex.getClass().getCanonicalName();
    }
}
