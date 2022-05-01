package com.coding.distributed.limiter;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LimiterService {

    // blockHandler 是位于 ExceptionUtil 类下的 handleException 静态方法，需符合对应的类型限制.
    @SentinelResource(
            value = "LimiterService.process",
            blockHandler = "handleException",
            blockHandlerClass = {LimiterExceptionUtil.class})
    public String process() {
        return "process";
    }

    // blockHandler 是位于当前类下的 exceptionHandler 方法，需符合对应的类型限制.
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler")
    public String hello(String name) {
        return String.format("Hello %s", name);
    }

    public String exceptionHandler(String name, BlockException ex) {
        // Do some log here.
        //        ex.printStackTrace();
        log.info("Oops, error occurred at " + name);
        return "Oops, error occurred at " + name;
    }
}
