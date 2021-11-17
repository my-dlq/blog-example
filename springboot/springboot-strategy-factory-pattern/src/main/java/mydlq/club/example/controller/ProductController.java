package mydlq.club.example.controller;

import mydlq.club.example.service.ProductService;
import mydlq.club.example.service.factory.ProductStrategyFactory;
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

    /**
     * 执行下单订购产品
     *
     * @param type 产品类型(策略)
     * @return 订购结果
     */
    @PostMapping("/order")
    public String order(@RequestParam(value = "type") String type) {
        ProductService productService = factoryForStrategy.getProductStrategy(type);
        return productService != null ? productService.orderingProduct() : "没有发现对应的产品处理策略";
    }

}
