package club.mydlq.example.controller;

import club.mydlq.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单 Controller
 *
 * @author mydlq
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResponseEntity<Long> createOrder() {
        Long orderId = orderService.create();
        return ResponseEntity.ok(orderId);
    }

    /**
     * 订单支付
     */
    @PostMapping("/pay")
    public void pay(@RequestParam Long orderId) {
        orderService.pay(orderId);
    }

    /**
     * 订单发货
     */
    @PostMapping("/ship")
    public void ship(@RequestParam Long orderId) {
        orderService.ship(orderId);
    }

    /**
     * 确认收货
     */
    @PostMapping("/receive")
    public void receive(@RequestParam Long orderId) {
        orderService.receive(orderId);
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public void cancel(@RequestParam Long orderId) {
        orderService.cancel(orderId);
    }

}
