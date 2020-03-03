package club.mydlq.example.sync;

import club.mydlq.entity.UserInfo;
import club.mydlq.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient 基本 get、post、form 表单等操作示例
 */
@Slf4j
public class BaseExample {

    /**
     * Http Get 请求示例
     */
    public static void httpGet() {
        CloseableHttpResponse response = null;
        try {
            // 创建 HttpGet 对象
            HttpGet httpGet = new HttpGet("http://localhost:8080/base/get?name=mydlq&sex=man");
            // 执行 Http Get 请求
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
     * Http Post Form 表单请求示例
     */
    public static void httpPostForm(){
        CloseableHttpResponse response = null;
        try {
            // 创建 HttpPost 对象
            HttpPost httpPost = new HttpPost("http://localhost:8080/base/form");
            // 设置 HttpPost 请求参数
            List<NameValuePair> params = new ArrayList<>(2);
            params.add(new BasicNameValuePair("name", "mydlq"));
            params.add(new BasicNameValuePair("sex", "man"));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            // 设置 Content-Type
            httpPost.addHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
            // 执行 Http Post 请求
            response = HttpClientUtil.getHttpclient().execute(httpPost);
            // 输出响应内容
            if (response.getEntity() != null) {
                log.info(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
            }
            // 销毁流
            EntityUtils.consume(urlEncodedFormEntity);
            EntityUtils.consume(response.getEntity());
        } catch (IOException | ParseException e) {
            log.error("",e);
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
     * Http Post Json 表单请求示例
     */
    public static void httpPostJson(){
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        try {
            // 创建 HttpPost 对象
            HttpPost httpPost = new HttpPost("http://localhost:8080/base/json");
            // 设置请求对象
            UserInfo requestUserInfo = new UserInfo();
            requestUserInfo.setName("mydlq");
            requestUserInfo.setSex("man");
            // 将请求对象通过 fastjson 中方法转换为 Json 字符串，并创建字符串实体对象
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(requestUserInfo), StandardCharsets.UTF_8);
            // 设置 HttpPost 请求参数
            httpPost.setEntity(stringEntity);
            // 设置 Content-Type
            httpPost.addHeader("Content-Type",ContentType.APPLICATION_JSON);
            // 执行 Http Post 请求
            response = HttpClientUtil.getHttpclient().execute(httpPost);
            // 输出响应内容
            if (response.getEntity() != null) {
                inputStream = response.getEntity().getContent();
                UserInfo userInfo = JSON.parseObject(inputStream, UserInfo.class);
                log.info(userInfo.toString());
            }
            // 销毁流
            EntityUtils.consume(stringEntity);
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            log.error("",e);
        } finally {
            // 释放资源
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
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
        // 执行 Http Get 请求
        //httpGet();
        // 执行 Http Post Form 表单请求
        //httpPostForm();
        // 执行 Http Post Json 请求
        httpPostJson();
    }

}
