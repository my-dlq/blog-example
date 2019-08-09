package club.mydlq.exceptionhandler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo {
    // 错误码
    private Integer code;
    // 错误信息
    private String message = "";
    // 返回结果
    private Object result = "";

    /**
     * 返回成功信息
     * @param result 返回的结果信息，一般指获取的资源
     */
    public void success(Object result) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.result = result;
    }

    /**
     * 返回错误信息
     * @param code 错误码
     * @param message 错误消息
     */
    public void error(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 返回错误信息
     * @param exceptionEnum 错误 Enum 枚举
     */
    public void error(ResultEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

}
