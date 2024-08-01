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
@RequestMapping("/api")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 下单接口
     * @param orderId 订单ID
     */
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestParam("orderId") Long orderId) {
        orderService.processOrder(orderId);
        return ResponseEntity.ok("success");
    }

}
