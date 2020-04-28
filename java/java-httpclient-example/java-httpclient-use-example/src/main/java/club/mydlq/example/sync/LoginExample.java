package club.mydlq.example.sync;

import club.mydlq.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * httpclient From 登录示例
 */
@Slf4j
public class LoginExample {

    /**
     * Form 表单登录，登录成功后会自动存 Session，然后再访问一个需要鉴权的页面
     */
    public static void httpFormLogin() {
        CloseableHttpResponse response = null;
        try {
            // 创建 Http 请求
            ClassicHttpRequest request = ClassicRequestBuilder.post("http://localhost:8080/login/from")
                    // 设置编码，防止中文乱码
                    .setCharset(StandardCharsets.UTF_8)  
                    .addParameter("t_username", "admin")
                    .addParameter("t_password", "123456")
                    .build();
            // 执行 Http 请求，进行登录
            response = HttpClientUtil.getHttpclient().execute(request);
            // 如果登录成功，则能获取 sessionid
            String jsessionid = HttpClientUtil.getCookieStore().getCookies().get(0).getValue();
            log.info("获取的 sessionid 为：{}", jsessionid);
            // 创建 HttpGet 对象
            HttpGet httpGet = new HttpGet("http://localhost:8080/login/test");
            // 执行 Http Get 请求与
            response = HttpClientUtil.getHttpclient().execute(httpGet);
            // 输出响应内容
            if (response.getEntity() != null) {
                log.info(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
            }
            // 销毁流
            EntityUtils.consume(response.getEntity());
        } catch (IOException | ParseException e) {
            log.error("", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    /** 测试 Main 方法 */
    public static void main(String[] args) {
        // 执行 Form 表单登录
        httpFormLogin();
    }

}
