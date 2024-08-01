package club.mydlq.example.service.impl;

import club.mydlq.example.model.OrderEvent;
import club.mydlq.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 订单 Service 实现类
 *
 * @author mydlq
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布自定义事件
     * @param orderId 订单ID
     */
    @Override
    public void processOrder(Long orderId) {
        // 模拟订单处理
        System.out.println("模拟对订单进行处理...");
        // 订单处理成功后发布订单事件
        System.out.println("发布订单事件，订单ID=" + orderId);
        applicationEventPublisher.publishEvent(new OrderEvent(this, orderId));
    }

}