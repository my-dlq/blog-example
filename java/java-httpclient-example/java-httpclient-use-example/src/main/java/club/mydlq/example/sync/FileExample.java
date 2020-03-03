package club.mydlq.example.sync;

import club.mydlq.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * httpclient 文件上传、下载示例
 */
@Slf4j
public class FileExample {

    /**
     * Http Post 上传文件示例
     */
    public static void httpPostUpload() {
        File file = new File("D:" +File.separator + "测试.xlsx");
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost("http://localhost:8080/file/upload");
            HttpEntity entity = MultipartEntityBuilder.create()
                    // 设置编码方式
                    .setCharset(StandardCharsets.UTF_8)
                    // 设置为兼容模式，解决返回中文乱码问题
                    .setMode(HttpMultipartMode.LEGACY)
                    .addPart("file", new FileBody(file))
                    .build();
            httpPost.setEntity(entity);
            // 执行提交
            response = HttpClientUtil.getHttpclient().execute(httpPost);
            // 输出响应内容
            if (response.getEntity() != null) {
                log.info(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
            }
            // 销毁流
            EntityUtils.consume(response.getEntity());
        } catch (IOException | ParseException e) {
            log.error("", e);
        } finally {
            // 释放资源
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    /**
     * Http Get 下载文件示例
     */
    public static void httpGetDownload() {
        CloseableHttpResponse response = null;
        try {
            // 创建 HttpGet 对象
            HttpGet httpGet = new HttpGet("http://localhost:8080/file/download");
            // 执行 Http Get 请求
            response = HttpClientUtil.getHttpclient().execute(httpGet);
            // 从 headers 中获取文件名
            String content = response.getHeader("Content-Disposition").getValue();
            String filename = content.substring(content.lastIndexOf('=') + 1, content.lastIndexOf('.'));
            String suffix = content.substring(content.lastIndexOf('.'));
            // 将文件名转码
            filename = URLDecoder.decode(filename, "UTF-8");
            // 获取响应实体对象
            HttpEntity entity = response.getEntity();
            if (entity != null){
                // 获取输入流并且将保存下载的文件
                try (InputStream inputStream = entity.getContent();
                     FileOutputStream fileOutputStream = new FileOutputStream("d://" + filename + suffix)
                ) {
                    byte[] tmp = new byte[1024];
                    int l;
                    while ((l = inputStream.read(tmp)) != -1) {
                        fileOutputStream.write(tmp, 0, l);
                    }
                    fileOutputStream.flush();
                }
            }
            // 销毁流
            EntityUtils.consume(response.getEntity());
        } catch (IOException | ProtocolException e) {
            log.error("", e);
        } finally {
            // 释放资源
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
        // 执行下载文件
        //httpGetDownload();
        // 执行上传文件
        httpPostUpload();
    }

}
