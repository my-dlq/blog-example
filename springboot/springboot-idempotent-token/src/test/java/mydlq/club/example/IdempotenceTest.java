package mydlq.club.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdempotenceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void interfaceIdempotenceTest() throws Exception {
        // 初始化 MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // 调用获取 Token 接口
        String token = mockMvc.perform(MockMvcRequestBuilders.get("/token")
                .accept(MediaType.TEXT_HTML))
                .andReturn()
                .getResponse().getContentAsString();
        log.info("获取的 Token 串：{}", token);
        // 循环调用 5 次进行测试
        for (int i = 1; i <= 5; i++) {
            log.info("第{}次调用测试接口", i);
            // 调用验证接口并打印结果
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/test")
                    .header("token", token)
                    .accept(MediaType.TEXT_HTML))
                    .andReturn().getResponse().getContentAsString();
            log.info(result);
            // 结果断言
            if (i == 0) {
                Assert.assertEquals(result, "正常调用");
            } else {
                Assert.assertEquals(result, "重复调用");
            }

        }
    }

}