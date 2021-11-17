package mydlq.club.example.service.factory;

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

    /**
     * 使用依赖注入引入 ProductService 产品实现类,以 Bean 名称作为 Map 的 Key,以 Bean 实现类作为 Value
     */
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
        return strategyMap.get(productName);
    }

}
