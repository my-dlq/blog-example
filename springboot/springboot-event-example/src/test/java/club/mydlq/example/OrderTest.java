package club.mydlq.example;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * 订单测试类
 *
 * @author mydlq
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
class OrderTest {

    @Resource
    private MockMvc mockMvc;

    /**
     * 模拟调用下单接口，触发订单事件
     */
    @Test
    public void testOrder() throws Exception {
        mockMvc.perform(post("/api/order").param("orderId", "10001"));
    }

}
