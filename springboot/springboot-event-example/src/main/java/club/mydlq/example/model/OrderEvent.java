package club.mydlq.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 订单事件
 *
 * @author mydlq
 */
@Setter
@Getter
public class OrderEvent extends ApplicationEvent {
    /**
     * 订单ID
     */
    private final Long orderId;

    /**
     * 构造函数
     * @param source  事件源
     * @param orderId 订单ID
     */
    public OrderEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }

}