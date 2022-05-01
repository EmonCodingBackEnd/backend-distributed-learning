package com.coding.distributed.id;

import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLeafServer
@SpringBootApplication
public class DistributedIdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedIdApplication.class, args);
    }
}
