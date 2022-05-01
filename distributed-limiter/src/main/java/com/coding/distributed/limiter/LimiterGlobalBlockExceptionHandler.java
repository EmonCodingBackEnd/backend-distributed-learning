package com.coding.distributed.limiter;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

// 如何返回JSON，SpringBoot全局异常处理！
@Slf4j
@Component
public class LimiterGlobalBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            BlockException e)
            throws Exception {
        log.info("被限流了......");
        // Return 429 (Too Many Requests) by default.
        httpServletResponse.setStatus(429);
        httpServletResponse.setContentType("text/html;charset=UTF-8");

        PrintWriter out = httpServletResponse.getWriter();
        out.print("被限流了......");
        out.flush();
        out.close();
    }
}
