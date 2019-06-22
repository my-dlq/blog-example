package mydlq.swagger.example;

import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Example Controller Document")
public class ExampleController {

    @GetMapping(value = "/example")
    @ApiOperation(value = "获取示例信息", notes = "用 Get 请求发送，获取示例设置的字符串信息。")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功处理请求"),
            @ApiResponse(code = 401, message = "没有权限访问该服务"),
            @ApiResponse(code = 403, message = "权限不足无法访问该服务"),
            @ApiResponse(code = 404, message = "未发现该微服务"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String getExample(
            @ApiParam(value = "输入一个 Key") @RequestParam(value = "key") String key,
            @ApiParam(value = "输入一个 Value", required = true) @RequestParam(value = "value") String value) {
        return "The value you enter is:" + key + "：" + value;
    }

    @PostMapping(value = "/example")
    @ApiOperation(value = "发送示例信息", notes = "Post方法，发送示例信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功处理请求"),
            @ApiResponse(code = 401, message = "没有权限访问该服务"),
            @ApiResponse(code = 403, message = "权限不足无法访问该服务"),
            @ApiResponse(code = 404, message = "未发现该微服务"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public ResponseEntity<User> postExample(@ApiParam(value = "用户信息") @RequestBody User user) {
        // 设置状态码，且设置默认值为200
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<User>(user,httpStatus);
    }

    @PutMapping(value = "/example")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功处理请求"),
            @ApiResponse(code = 201, message = "被创建"),
            @ApiResponse(code = 401, message = "没有权限访问该服务"),
            @ApiResponse(code = 403, message = "权限不足无法访问该服务"),
            @ApiResponse(code = 404, message = "未发现该微服务"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @ApiOperation(value = "修改示例信息", notes = "Put方法，修改示例信息")
    public ResponseEntity<User> putExample(@ApiParam(value = "用户信息") @RequestBody User user) {
        // 设置状态码，且设置默认值为200
        HttpStatus httpStatus = HttpStatus.OK;
        // 设置 Headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
        // 错误就发送 500 错误
        if (user == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<User>(user, httpHeaders, httpStatus);
    }

    @DeleteMapping(value = "/example/{key}")
    @ApiOperation(value = "删除示例信息", notes = "Delete方法，删除示例信息。")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功处理请求"),
            @ApiResponse(code = 204, message = "成功处理请求，服务器无返回内容"),
            @ApiResponse(code = 401, message = "没有权限访问该服务"),
            @ApiResponse(code = 403, message = "权限不足无法访问该服务"),
            @ApiResponse(code = 404, message = "未发现该微服务"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public void deleteExample(@ApiParam(value = "输入一个 Key") @PathVariable(value = "key") String key) {
        System.out.println("delete info " + key);
    }

}
