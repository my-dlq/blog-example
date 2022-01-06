package club.mydlq.controller;

import club.mydlq.mappers.TableMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 数据库表操作 Controller
 *
 * @author mydlq
 */
@RestController
public class TestController {

    @Resource
    private TableMapper tableMapper;

    /**
     * 创建数据库表
     *
     * @param tableName 表名称
     * @return 是否创建成功
     */
    @PostMapping("/createTable")
    public ResponseEntity<String> createTableTest(@RequestParam String tableName) {
        try {
            // 创建数据库表
            tableMapper.createTable(tableName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("创建数据库表失败");
        }
        return ResponseEntity.ok("创建数据库表成功");
    }

}