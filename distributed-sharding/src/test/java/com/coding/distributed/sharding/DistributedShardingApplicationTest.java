package com.coding.distributed.sharding;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistributedShardingApplicationTest {
    @Autowired private OrderDao orderDao;

    @Test
    public void insert() {
        for (int i = 0; i < 10; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserId(new Random().nextInt(999));
            orderDao.save(orderEntity);
        }
    }

    @Test
    public void findByOrderId() {
        OrderEntity orderEntity = orderDao.findByOrderId(727210051206885376L);
        log.info("orderEntity={}", orderEntity);
    }

    @Test
    public void findByOrderIdFromWriteDB() {
        // 从主库查询
        HintManager.getInstance().setWriteRouteOnly();
        OrderEntity orderEntity = orderDao.findByOrderId(727210051269799937L);
        log.info("orderEntity={}", orderEntity);
    }

    @Test
    public void findByUserId() {
        List<OrderEntity> orderEntityList = orderDao.findByUserId(763);
        log.info("orderEntityList={}", orderEntityList);
    }

    @Test
    public void upadte() {
        OrderEntity orderEntity = orderDao.findByOrderId(726585557152432129L);
        orderEntity.setUserId(998);
        orderDao.save(orderEntity);
    }
}
