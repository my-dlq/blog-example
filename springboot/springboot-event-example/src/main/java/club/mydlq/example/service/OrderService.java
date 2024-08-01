package club.mydlq.example.service;

import org.springframework.stereotype.Component;

/**
 * 订单 Service
 *
 * @author mydlq
 */
@Component
public interface OrderService {

    /**
     * 发布自定义事件
     * @param orderId 订单ID
     */
    void processOrder(Long orderId);

}