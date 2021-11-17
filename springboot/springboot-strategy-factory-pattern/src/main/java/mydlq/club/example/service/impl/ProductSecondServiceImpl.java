package mydlq.swagger.example.service.impl;

import mydlq.club.example.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * 产品B Service
 *
 * @author mydlq
 */
@Service("productB")
public class ProductSecondServiceImpl implements ProductService {

    @Override
    public String orderingProduct() {
        // 执行产品订购逻辑
        //....
        return "成功订购产品B";
    }

}