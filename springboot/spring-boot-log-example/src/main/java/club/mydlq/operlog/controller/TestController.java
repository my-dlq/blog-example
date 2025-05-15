package club.mydlq.operlog.controller;

import club.mydlq.operlog.syslog.enums.OperTypeEnum;
import club.mydlq.operlog.model.QueryParam;
import club.mydlq.operlog.syslog.annotation.OperLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 测试 Controller
 *
 * @author mydlq
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     *  URL入参的接口
     */
    @GetMapping("/query")
    @OperLog(module = "模块1", description = "测试 Url 传参", operType = OperTypeEnum.QUERY)
    public ResponseEntity<Object> queryTest(@RequestParam("param1") String param1,
                                            @RequestParam("param2") String param2) {
        return ResponseEntity.ok("ok");
    }

    /**
     * FORM表单入参的接口
     * @param queryParam 错误信息
     * @return 执行结果
     */
    @PostMapping("/form")
    @OperLog(module = "模块2", description = "测试 Form 表单", operType = OperTypeEnum.INSERT)
    public ResponseEntity<Object> formTest(QueryParam queryParam) {
        return ResponseEntity.ok("ok");
    }

    /**
     * JSON入参的接口
     * @param queryParam 错误信息
     * @return 执行结果
     */
    @PostMapping("/json")
    @OperLog(module = "模块3", description = "测试 JSON 传参", operType = OperTypeEnum.UPDATE)
    public ResponseEntity<Object> jsonTest(@RequestBody QueryParam queryParam) {
        return ResponseEntity.ok("ok");
    }

    /**
     * 测试错误信息是否记录的接口
     * @return 执行结果
     */
    @GetMapping("/error")
    @OperLog(module = "模块4", description = "测试发生错误情况", operType = OperTypeEnum.QUERY)
    public ResponseEntity<Object> errorTest() {
        // 造成一个异常
        if (true) {
            throw new RuntimeException("发生错误");
        }
        return ResponseEntity.ok("ok");
    }

}
