package club.mydlq.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.TimeValue;

/**
 * httpclient （异步）工具
 */
@Slf4j
public class HttpClientAsycnUtil {

    /**
     * HttpAsyncClient 对象
     */
    private static CloseableHttpAsyncClient closeableHttpAsyncClient = null;

    // HttpAsyncclient 初始化
    static {
        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .build();
        // Http 异步连接池
        PoolingAsyncClientConnectionManager poolingAsyncClientConnectionManager = new PoolingAsyncClientConnectionManager();
        poolingAsyncClientConnectionManager.setMaxTotal(200);
        poolingAsyncClientConnectionManager.setDefaultMaxPerRoute(200);
        poolingAsyncClientConnectionManager.setValidateAfterInactivity(TimeValue.ofMinutes(5));
        // HttpAsyncClient
        closeableHttpAsyncClient = HttpAsyncClients.custom()
                .setIOReactorConfig(ioReactorConfig)
                .setConnectionManager(poolingAsyncClientConnectionManager)
                .build();
        // 运行
        closeableHttpAsyncClient.start();
    }

    /**
     * 获取 HttpAsyncclient 对象
     *
     * @return CloseableHttpAsyncClient
     */
    public static CloseableHttpAsyncClient getHttpclient() {
        return closeableHttpAsyncClient;
    }

}
