package club.mydlq.example.model;

import club.mydlq.example.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单信息实体类
 *
 * @author mydlq
 */
@Data
@AllArgsConstructor
public class Order {
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单状态
     */
    private OrderState orderState;
}
