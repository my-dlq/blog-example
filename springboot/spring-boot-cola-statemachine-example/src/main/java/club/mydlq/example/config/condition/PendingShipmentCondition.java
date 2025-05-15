package club.mydlq.example.config.condition;

import club.mydlq.example.enums.OrderState;
import club.mydlq.example.model.Order;
import com.alibaba.cola.statemachine.Condition;
import org.springframework.stereotype.Component;

/**
 * 待发货状态转换条件
 * (1) CREATED(已创建) --> PENDING_SHIPMENT(待发货)
 *
 * @author mydlq
 */
@Component
public class PendingShipmentCondition implements Condition<Order> {

    @Override
    public boolean isSatisfied(Order context) {
        return context != null
                && context.getOrderId() != null
                && context.getOrderState() == OrderState.CREATED;
    }

}
