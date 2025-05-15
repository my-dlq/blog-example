package club.mydlq.example.config.action;

import club.mydlq.example.enums.OrderEvent;
import club.mydlq.example.enums.OrderState;
import club.mydlq.example.mapper.OrderMapper;
import club.mydlq.example.model.Order;
import com.alibaba.cola.statemachine.Action;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 订单状态转换动作
 *
 * @author mydlq
 */
@Slf4j
@Component
public class OrderAction implements Action<OrderState, OrderEvent, Order> {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void execute(OrderState from, OrderState to, OrderEvent event, Order context) {
        // 打印日志
        System.out.printf("订单ID:%s: %s -> (%s) -> %s %n", context.getOrderId(), from, event, to);
        // 更新订单状态
        context.setOrderState(to);
        orderMapper.update(context);
    }

}
