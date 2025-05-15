package club.mydlq.example.service;

/**
 * 订单 Service
 *
 * @author mydlq
 */
public interface OrderService {
    /**
     * 创建订单
     * @return 订单ID
     */
    Long create();

    /**
     * 支付
     * @param orderId 订单ID
     */
    void pay(Long orderId);

    /**
     * 发货
     * @param orderId 订单ID
     */
    void ship(Long orderId);

    /**
     * 确认收货
     * @param orderId 订单ID
     */
    void receive(Long orderId);

    /**
     * 取消订单
     * @param orderId 订单ID
     */
    void cancel(Long orderId);
}
