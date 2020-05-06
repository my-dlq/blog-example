package club.mydlq.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.TimeValue;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * httpclient （同步）工具
 */
@Slf4j
public class HttpClientUtil {

    private HttpClientUtil(){}

    /** HttpClient 对象 */
    private static CloseableHttpClient httpClient = null;
    /** CookieStore 对象 */
    private static CookieStore cookieStore = null;
    /** Basic Auth 管理对象 **/
    private static BasicCredentialsProvider basicCredentialsProvider = null;

    // Httpclient 初始化
    static {
        // 注册访问协议相关的 Socket 工厂
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        // Http 连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        poolingHttpClientConnectionManager.setDefaultSocketConfig(SocketConfig.custom()
                .setSoTimeout(30,TimeUnit.SECONDS)
                .setTcpNoDelay(true).build()
        );
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(200);
        poolingHttpClientConnectionManager.setValidateAfterInactivity(TimeValue.ofMinutes(5));
        // Http 请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000, TimeUnit.MILLISECONDS)
                .setResponseTimeout(5000, TimeUnit.MILLISECONDS)
                .setConnectionRequestTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        // 设置 Cookie
        cookieStore = new BasicCookieStore();
        // 设置 Basic Auth 对象
        basicCredentialsProvider = new BasicCredentialsProvider();
        // 创建监听器，在 JVM 停止或重启时，关闭连接池释放掉连接
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                log.info("执行关闭 HttpClient");
                httpClient.close();
                log.info("已经关闭 HttpClient");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }));
        // 创建 HttpClient 对象
        httpClient = HttpClients.custom()
                // 设置 Cookie
                .setDefaultCookieStore(cookieStore)
                // 设置 Basic Auth
                .setDefaultCredentialsProvider(basicCredentialsProvider)
                // 设置 HttpClient 请求参数
                .setDefaultRequestConfig(requestConfig)
                // 设置连接池
                .setConnectionManager(poolingHttpClientConnectionManager)
                // 设置定时清理连接池中过期的连接
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofMinutes(3))
                .build();
    }

    /**
     * 获取 Httpclient 对象
     *
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient getHttpclient() {
        return httpClient;
    }

    /**
     * 获取 CookieStore 对象
     *
     * @return CookieStore
     */
    public static CookieStore getCookieStore() {
        return cookieStore;
    }

    /**
     * 获取 BasicCredentialsProvider 对象
     *
     * @return BasicCredentialsProvider
     */
    public static BasicCredentialsProvider getBasicCredentialsProvider(){
        return basicCredentialsProvider;
    }

}
