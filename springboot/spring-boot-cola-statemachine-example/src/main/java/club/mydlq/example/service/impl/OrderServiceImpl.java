package club.mydlq.example.service.impl;

import club.mydlq.example.enums.OrderEvent;
import club.mydlq.example.enums.OrderState;
import club.mydlq.example.mapper.OrderMapper;
import club.mydlq.example.model.Order;
import club.mydlq.example.service.OrderService;
import club.mydlq.example.utils.OrderIdUtil;
import com.alibaba.cola.statemachine.StateMachine;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单 Service
 *
 * @author mydlq
 */
@Service
public class OrderServiceImpl implements OrderService {
    /**
     * 订单持久化 Mapper
     */
    @Resource
    private OrderMapper orderMapper;
    /**
     * 订单状态机
     */
    @Resource(name = "orderStateMachine")
    StateMachine<OrderState, OrderEvent, Order> orderOperaMachine;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create() {
        // 生成订单ID
        Long orderId = OrderIdUtil.generateOrderId();
        // 创建订单并设置订单初始状态
        Order order = new Order(orderId, OrderState.CREATED);
        // 执行订单创建的其它操作
        // ...
        // 保存订单信息
        orderMapper.save(order);
        return orderId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pay(Long orderId) {
        // 查询订单信息
        Order order = this.queryOrder(orderId);
        // 触发状态机状态变更
        OrderState targetState = orderOperaMachine.fireEvent(order.getOrderState(), OrderEvent.PAY, order);
        // 验证目标状态
        if (targetState != OrderState.PENDING_SHIPMENT) {
            throw new RuntimeException("订单ID=" + orderId + "支付失败");
        }
        // 执行订单支付的其它操作
        // ......
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ship(Long orderId) {
        // 查询订单信息
        Order order = this.queryOrder(orderId);
        // 触发状态机状态变更
        OrderState targetState = orderOperaMachine.fireEvent(order.getOrderState(), OrderEvent.SHIP, order);
        // 验证目标状态
        if (targetState != OrderState.SHIPPED) {
            throw new RuntimeException("订单ID " + orderId + " 发货失败");
        }
        // 执行订单发货的其它操作
        // ......
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receive(Long orderId) {
        // 查询订单信息
        Order order = this.queryOrder(orderId);
        // 触发状态机状态变更
        OrderState targetState = orderOperaMachine.fireEvent(order.getOrderState(), OrderEvent.RECEIVE, order);
        // 验证目标状态
        if (targetState != OrderState.RECEIVED) {
            throw new RuntimeException("订单ID " + orderId + " 确认收货失败");
        }
        // 执行确认收货的其它操作
        // ......
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancel(Long orderId) {
        // 查询订单信息
        Order order = this.queryOrder(orderId);
        // 触发状态机状态变更
        OrderState targetState = orderOperaMachine.fireEvent(order.getOrderState(), OrderEvent.CANCEL, order);
        // 验证目标状态
        if (targetState != OrderState.CLOSED) {
            throw new RuntimeException("订单ID " + orderId + " 取消订单失败: ");
        }
        // 执行取消订单的其它操作
        // ......
    }

    /**
     * 查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    private Order queryOrder(Long orderId) {
        // 查询订单信息
        Order order = orderMapper.selectByOrderId(orderId);
        if (order == null) {
            throw new RuntimeException("订单ID " + orderId + " 的订单不存在");
        }
        return order;
    }

}
