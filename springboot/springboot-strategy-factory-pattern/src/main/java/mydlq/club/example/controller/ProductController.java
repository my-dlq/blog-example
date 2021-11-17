package mydlq.swagger.example.controller;

import mydlq.swagger.example.service.factory.ProductStrategyFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 测试 Controller
 *
 * @author mydlq
 */
@RestController
public class ProductController {

    @Resource
    private ProductStrategyFactory factoryForStrategy;

    @PostMapping("/order")
    public String order(@RequestParam(value = "type") String type) {
        return factoryForStrategy.getProductStrategy(type).orderingProduct();
    }

}
