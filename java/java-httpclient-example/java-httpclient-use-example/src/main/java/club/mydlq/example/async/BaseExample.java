package club.mydlq.example.async;

import club.mydlq.utils.HttpClientAsycnUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequests;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * HttpClient 异步请求基本操作示例
 */
@Slf4j
public class BaseExample {

    /**
     * Http 异步请求
     */
    public static void asyncRequest() {
        try {
            HttpHost httpHost = new HttpHost("http","localhost",8080);
            SimpleHttpRequest simpleHttpRequest = SimpleHttpRequests.create(Method.GET,httpHost,"/base/get?name=test&sex=man");
            final Future<SimpleHttpResponse> future = HttpClientAsycnUtil.getHttpclient()
                    .execute(simpleHttpRequest, new FutureCallback<SimpleHttpResponse>() {

                        @Override
                        public void completed(final SimpleHttpResponse response) {
                            log.info(response.toString());
                        }

                        @Override
                        public void failed(final Exception ex) {
                            log.error("执行请求失败：", ex);
                        }

                        @Override
                        public void cancelled() {
                            log.info("取消请求");
                        }

                    });
            String responseBody = future.get().getBody().getBodyText();
            log.info(responseBody);
        } catch (ExecutionException | InterruptedException e) {
            log.error("", e);
            Thread.currentThread().interrupt();
        }
    }

    /** 测试 Main 方法 */
    public static void main(final String[] args) {
        asyncRequest();
    }

}
