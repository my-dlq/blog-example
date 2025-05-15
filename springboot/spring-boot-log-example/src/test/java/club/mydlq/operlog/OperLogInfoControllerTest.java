package club.mydlq.operlog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * 测试操作日志
 *
 * @author mydlq
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class OperLogInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 调用不同的接口，测试是否记录操作日志
     */
    @Test
    public void testOperLog() throws Exception {
        // 1 - 访问URL入参的接口进行测试
        mockMvc.perform(get("/test/query")
                .param("param1", "1")
                .param("param2", "2"));
        // 2 - 访问FORM表单入参的接口
        mockMvc.perform(post("/test/form")
                .param("param1", "1")
                .param("param2", "2"));
        // 3 - 访问JSON入参的接口
        mockMvc.perform(post("/test/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"param1\":1,\"param2\":2}"));
        // 4 - 访问测试错误信息是否记录的接口(验证操作日志中是否会记录错误信息)
        mockMvc.perform(get("/test/error"));
    }

}

