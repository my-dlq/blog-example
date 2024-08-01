package club.mydlq.example.listener;

import club.mydlq.example.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步事件监听
 *
 * @author mydlq
 */
@Slf4j
@Component
public class OrderEventListener {

    /**
     * 监听订单事件，发送邮件 (这里使用 @Async 注解，实现对事件进行异步处理)
     * @param orderEvent 订单事件
     */
    @Async("asyncExecutor")
    @EventListener
    public void handleCustomEvent(OrderEvent orderEvent) {
        System.out.println("监听到订单事件");
        sendEmail(orderEvent.getOrderId());
    }

    /**
     * 发送邮件
     * @param orderId 订单ID
     */
    private void sendEmail(long orderId) {
        // 模拟发送邮件
        System.out.println("发送订单邮件，订单ID=" + orderId);
    }

}