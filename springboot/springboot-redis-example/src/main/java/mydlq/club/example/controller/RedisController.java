package mydlq.club.example.controller;

import mydlq.club.example.service.RedisBatch;
import mydlq.club.example.service.RedisString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redis 操作 Controller
 *
 * @author mydlq
 */
@RestController
public class RedisController {

    @Autowired
    private RedisBatch redisBatch;

    @Autowired
    private RedisString redisString;

    /**
     * 存值
     *
     * @param key   键
     * @param value 值
     * @return 存储结果
     */
    @GetMapping("/set")
    public String redisStringSet(@RequestParam String key, @RequestParam String value) {
        redisString.set(key, value);
        return "存储成功";
    }

    /**
     * 取值
     *
     * @param key 键
     * @return 值
     */
    @GetMapping("/get")
    public Object redisStringGet(@RequestParam String key) {
        return redisString.get(key);
    }

    /**
     * 批量存值
     */
    @GetMapping("/batch/set")
    public void redisBatchSet() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("test1", "value1");
        map.put("test2", "value2");
        map.put("test3", "value3");
        redisBatch.barchSet(map);
    }

    /**
     * 批量取值
     *
     * @return 值列表
     */
    @GetMapping("/batch/get")
    public List<Object> redisBatchGet() {
        // 批量取值，如果某个 key 不存在，则值为 null
        List<String> list = new ArrayList<>(3);
        list.add("test1");
        list.add("test2");
        list.add("test3");
        return redisBatch.batchGet(list);
    }

}
