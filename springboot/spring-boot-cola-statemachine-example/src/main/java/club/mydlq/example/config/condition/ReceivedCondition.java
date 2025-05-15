package club.mydlq.example.config.condition;

import club.mydlq.example.enums.OrderState;
import club.mydlq.example.model.Order;
import com.alibaba.cola.statemachine.Condition;
import org.springframework.stereotype.Component;

/**
 * 已收货状态转换条件
 * (1) SHIPPED(已发货) --> RECEIVED(已收货)
 *
 * @author mydlq
 */
@Component
public class ReceivedCondition implements Condition<Order> {

    @Override
    public boolean isSatisfied(Order context) {
        return context != null
                && context.getOrderId() != null
                && context.getOrderState() == OrderState.SHIPPED;
    }

}
