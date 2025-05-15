package club.mydlq.example.config;

import club.mydlq.example.config.action.*;
import club.mydlq.example.config.condition.ClosedCondition;
import club.mydlq.example.config.condition.PendingShipmentCondition;
import club.mydlq.example.config.condition.ReceivedCondition;
import club.mydlq.example.config.condition.ShippedCondition;
import club.mydlq.example.enums.OrderEvent;
import club.mydlq.example.enums.OrderState;
import club.mydlq.example.model.Order;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 订单状态机配置
 *
 * @author mydlq
 */
@Configuration
public class OrderStateMachineConfig {

    /**
     * 条件
     */
    @Resource(name = "pendingShipmentCondition")
    private PendingShipmentCondition pendingShipmentCondition;
    @Resource(name = "shippedCondition")
    private ShippedCondition shippedCondition;
    @Resource(name = "receivedCondition")
    private ReceivedCondition receivedCondition;
    @Resource(name = "closedCondition")
    private ClosedCondition closedCondition;
    /**
     * 动作
     */
    @Resource(name = "orderAction")
    private OrderAction orderAction;

    /**
     * 状态机ID
     */
    private static final String STATE_MACHINE_ID = "orderStateMachine";

    @Bean("orderStateMachine")
    public StateMachine<OrderState, OrderEvent, Order> orderStateMachine() {
        // (1) 生成一个状态机builder
        StateMachineBuilder<OrderState, OrderEvent, Order> builder = StateMachineBuilderFactory.create();

        // (2) 通过使用builder配置外部状态转换
        // - ①状态转换规则1: CREATED(已创建) -> PAY(支付) -> PENDING_SHIPMENT(待发货)
        builder.externalTransition()
                .from(OrderState.CREATED).to(OrderState.PENDING_SHIPMENT).on(OrderEvent.PAY)
                .when(pendingShipmentCondition)
                .perform(orderAction);
        // - ②状态转换规则2: PENDING_SHIPMENT(待发货) -> SHIP(发货) -> SHIPPED(已发货)
        builder.externalTransition()
                .from(OrderState.PENDING_SHIPMENT).to(OrderState.SHIPPED).on(OrderEvent.SHIP)
                .when(shippedCondition)
                .perform(orderAction);
        // - ③状态转换规则3: SHIPPED(已发货) -> RECEIVE(收货) -> RECEIVED(已收货)
        builder.externalTransition()
                .from(OrderState.SHIPPED).to(OrderState.RECEIVED).on(OrderEvent.RECEIVE)
                .when(receivedCondition)
                .perform(orderAction);
        // - ④状态转换规则4:
        //     CREATED(已创建)          -> CANCEL(取消) -> CLOSED(已关闭)
        //     PENDING_SHIPMENT(待发货) -> CANCEL(取消) -> CLOSED(已关闭)
        //     SHIPPED(已发货)          -> CANCEL(取消) -> CLOSED(已关闭)
        builder.externalTransitions()
                .fromAmong(OrderState.CREATED,
                        OrderState.PENDING_SHIPMENT,
                        OrderState.SHIPPED)
                .to(OrderState.CLOSED)
                .on(OrderEvent.CANCEL)
                .when(closedCondition)
                .perform(orderAction);

        // (3) 构建状态机
        StateMachine<OrderState, OrderEvent, Order> orderStateMachine = builder.build(STATE_MACHINE_ID);

        // (4) 输出状态机转换流程plantUML
        System.out.println(orderStateMachine.generatePlantUML());

        return orderStateMachine;
    }

}
