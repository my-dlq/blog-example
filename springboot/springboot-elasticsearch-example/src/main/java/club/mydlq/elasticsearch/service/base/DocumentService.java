package club.mydlq.elasticsearch.service.base;

import club.mydlq.elasticsearch.model.entity.UserInfo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;

/**
 * 文档操作
 */
@Slf4j
@Service
public class DocumentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public Object existsDocument() {
        Object result = "";
        try {
            // 获取请求对象
            GetRequest getRequest = new GetRequest("mydlq-user", "doc", "1");
            // 是否获取源码内容
            getRequest.fetchSourceContext(new FetchSourceContext(false));
            // 执行请求，验证文档是否存在
            boolean isExist = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
            log.info("文档是否存在：{}", isExist);
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将结果返回
            result = isExist;
        } catch (IOException e) {
            log.error("", e);
        }
        return result;
    }

    public Object getDocument() {
        Object result = "";
        try {
            // 获取请求对象
            GetRequest getRequest = new GetRequest("mydlq-user", "doc", "1");
            // 获取文档信息
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            // 将 JSON 转换成对象
            if (getResponse.isExists()) {
                UserInfo userInfo = JSON.parseObject(getResponse.getSourceAsBytes(), UserInfo.class);
                log.info("用户信息：{}", userInfo);
            }
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将结果返回
            result = getResponse.getSourceAsString();
        } catch (IOException e) {
            log.error("", e);
        }
        return result;
    }

    public Object addDocument() {
        Object result = "";
        try {
            // 创建索引请求对象
            IndexRequest indexRequest = new IndexRequest("mydlq-user", "doc", "1");
            // 创建用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setName("张三");
            userInfo.setAge(29);
            userInfo.setSalary(100.00f);
            userInfo.setAddress("北京市");
            userInfo.setRemark("来自北京市的张先生");
            userInfo.setCreateTime(new Date());
            userInfo.setBirthDate("1990-01-10");
            // 将对象转换为 byte 数组
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置文档内容
            indexRequest.source(json, XContentType.JSON);
            // 执行增加文档
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("创建状态：{}", response.status());
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将结果返回
            result = response.getResult();
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    public Object updateDocument() {
        Object result = "";
        try {
            // 创建索引请求对象
            UpdateRequest updateRequest = new UpdateRequest("mydlq-user", "doc", "1");
            // 设置用户更新信息
            UserInfo userInfo = new UserInfo();
            userInfo.setSalary(200.00f);
            userInfo.setAddress("北京市海淀区");
            // 将对象转换为 byte 数组
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置更新文档内容
            updateRequest.doc(json, XContentType.JSON);
            // 执行更新文档
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            log.info("创建状态：{}", response.status());
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将结果返回
            result = response.getResult();
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    public Object deleteDocument() {
        Object result = "";
        try {
            // 创建删除请求对象
            DeleteRequest deleteRequest = new DeleteRequest("mydlq-user", "doc", "1");
            // 执行删除文档
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("删除状态：{}", response.status());
            // 根据具体业务逻辑返回不同结果，这里为了方便直接将结果返回
            result = response.getResult();
        } catch (IOException e) {
            log.error("", e);
        }
        return result;
    }

}
