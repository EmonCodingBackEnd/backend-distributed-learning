package com.coding.distributed.id.controller;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.service.SegmentService;
import com.sankuai.inf.leaf.service.SnowflakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdController {

    @Autowired SegmentService segmentService;

    @Autowired private SnowflakeService snowflakeService;

    @GetMapping("/segment")
    public Result segment() {
        Result result = segmentService.getId("leaf-segment-test");
        return result;
    }

    @GetMapping("/snowflake")
    public Result snowflake() {
        Result result = snowflakeService.getId("emon");
        return result;
    }
}
