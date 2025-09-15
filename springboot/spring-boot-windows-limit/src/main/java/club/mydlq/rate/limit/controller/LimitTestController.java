package club.mydlq.rate.limit.controller;

import club.mydlq.rate.limit.common.RateLimit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流测试
 *
 * @author mydlq
 */
@RestController
@RequestMapping("/test")
public class LimitTestController {

    @RateLimit(key = "test-key", time = 10, limit = 5)
    @GetMapping("/query")
    public ResponseEntity<String> testLimit() {
        return ResponseEntity.ok("OK");
    }

}
