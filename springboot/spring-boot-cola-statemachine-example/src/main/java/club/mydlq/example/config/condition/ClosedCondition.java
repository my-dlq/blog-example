package club.mydlq.example.config.condition;

import club.mydlq.example.enums.OrderState;
import club.mydlq.example.model.Order;
import com.alibaba.cola.statemachine.Condition;
import org.springframework.stereotype.Component;

/**
 * 已关闭状态转换条件
 * (1) CREATED(已创建)          --> CLOSED(已关闭)
 * (2) PENDING_SHIPMENT(待发货) --> CLOSED(已关闭)
 * (3) SHIPPED(已发货)          --> CLOSED(已关闭)
 *
 * @author mydlq
 */
@Component
public class ClosedCondition implements Condition<Order> {

    @Override
    public boolean isSatisfied(Order context) {
        return context != null
                && context.getOrderId() != null
                && (context.getOrderState() == OrderState.CREATED
                        || context.getOrderState() == OrderState.PENDING_SHIPMENT
                        || context.getOrderState() == OrderState.SHIPPED);
    }

}
