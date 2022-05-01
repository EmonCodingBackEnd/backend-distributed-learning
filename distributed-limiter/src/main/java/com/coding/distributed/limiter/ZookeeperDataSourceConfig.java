package com.coding.distributed.limiter;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.zookeeper.ZookeeperDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZookeeperDataSourceConfig {
    @Value("${sentinel.client.zookeeper.address}")
    private String zooServer;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public void loadRules() {
        final String remoteAddress = zooServer;
        final String path = String.format("/%s/sentinel-flow-rules", appName);

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource =
                new ZookeeperDataSource<>(
                        remoteAddress,
                        path,
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
