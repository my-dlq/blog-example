package mydlq.swagger.example.service.factory;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import mydlq.club.example.service.ProductService;

/**
 * 产品策略工厂
 *
 * @author mydlq
 */
@Component
public class ProductStrategyFactory {

    @Resource
    Map<String, ProductService> strategyMap = new ConcurrentHashMap<>(2);

    /**
     * 查找对应的产品的处理策略
     *
     * @param productName 产品名称
     * @return 对应的产品订购逻辑实现策略
     */
    public ProductService getProductStrategy(String productName) {
        // 根据从 productName 从 strategyMap 集合中查询对应的产品下单策略
        ProductService product = strategyMap.get(productName);
        // 验证是否根据输入的产品名称查找到对应的产品下单策略，如果没有则抛出异常
        if (product == null) {
            throw new RuntimeException("没有发现对应的产品处理策略");
        }
        return product;
    }

}
