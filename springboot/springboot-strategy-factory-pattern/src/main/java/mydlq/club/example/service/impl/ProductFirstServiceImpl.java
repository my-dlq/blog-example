package mydlq.club.example.service.impl;

import mydlq.club.example.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * 产品A Service
 *
 * @author mydlq
 */
@Service("productA")
public class ProductFirstServiceImpl implements ProductService {

    @Override
    public String orderingProduct() {
        // 执行产品订购逻辑
        //....
        return "成功订购产品A";
    }

}