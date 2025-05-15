package clue.mydlq.example;

import club.mydlq.example.Application;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 订单状态转换测试
 *
 * @author mydlq
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 调用下单接口，获取一个订单ID
     */
    public String createOrder() throws Exception {
        // 模拟调用下单接口，获取一个订单ID
        return mockMvc.perform(post("/order/create"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    /**
     * (1) 测试状态转换: 已创建 -> (支付) -> 待发货
     */
    @Test
    @Order(1)
    public void testCreatedToPendingShipment() throws Exception {
        System.out.println("---(1) 测试状态转换: 已创建 -> (支付) -> 待发货---");
        String orderId = createOrder();                                     //下单
        mockMvc.perform(post("/order/pay").param("orderId", orderId));      //支付
    }

    /**
     * (2) 测试状态转换: 待发货 -> (发货) -> 已发货
     */
    @Test
    @Order(2)
    public void testPendingShipmentToShipped() throws Exception {
        System.out.println("\n---(2) 测试状态转换: 待发货 -> (发货) -> 已发货---" );
        String orderId = createOrder();                                     //下单
        mockMvc.perform(post("/order/pay").param("orderId", orderId));      //支付
        mockMvc.perform(post("/order/ship").param("orderId", orderId));     //发货
    }

    /**
     * (3) 测试状态转换: 已发货 -> (收货) -> 已收货
     */
    @Test
    @Order(3)
    public void testShippedToReceived() throws Exception {
        System.out.println("\n---(3) 测试状态转换: 已发货 -> (收货) -> 已收货---");
        String orderId = createOrder();                                    //下单
        mockMvc.perform(post("/order/pay").param("orderId", orderId));     //支付
        mockMvc.perform(post("/order/ship").param("orderId", orderId));    //发货
        mockMvc.perform(post("/order/receive").param("orderId", orderId)); //收货
    }

    /**
     * (4) 测试状态转换: 已创建 -> (取消) -> 已取消
     */
    @Test
    @Order(4)
    public void testCreatedToCanceled() throws Exception {
        System.out.println("\n---(4) 测试状态转换: 已创建 -> (取消) -> 已关闭---");
        String orderId = createOrder();                                    //下单
        mockMvc.perform(post("/order/cancel").param("orderId", orderId));  //取消
    }

    /**
     * (5) 测试状态转换: 待发货 -> (取消) -> 已取消
     */
    @Test
    @Order(5)
    public void testPendingShipmentToCanceled() throws Exception {
        System.out.println("\n---(5) 测试状态转换: 待发货 -> (取消) -> 已关闭---");
        String orderId = createOrder();                                    //下单
        mockMvc.perform(post("/order/pay").param("orderId", orderId));     //支付
        mockMvc.perform(post("/order/cancel").param("orderId", orderId));  //取消
    }

    /**
     * (6) 测试状态转换: 已发货 -> (取消) -> 已取消
     */
    @Test
    @Order(6)
    public void testShippedToCanceled() throws Exception {
        System.out.println("\n---(6) 测试状态转换: 已发货 -> (取消) -> 已关闭---");
        String orderId = createOrder();                                    //下单
        mockMvc.perform(post("/order/pay").param("orderId", orderId));     //支付
        mockMvc.perform(post("/order/ship").param("orderId", orderId));    //发货
        mockMvc.perform(post("/order/cancel").param("orderId", orderId));  //取消
    }

}

