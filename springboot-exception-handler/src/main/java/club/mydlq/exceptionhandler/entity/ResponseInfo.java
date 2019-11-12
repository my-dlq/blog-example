package club.mydlq.exceptionhandler.entity;

import club.mydlq.exceptionhandler.enums.ResultEnum;
import lombok.Data;

@Data
public class ResponseInfo {
    // 错误码
    private Integer code;
    // 错误信息
    private String message = "";
    // 返回结果
    private Object data = "";

    public ResponseInfo() {
    }

    /**
     * 设置 code 与 message 构造方法
     * @param code    返回码
     * @param message 返回消息
     */
    public ResponseInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举信息的构造方法
     * @param resultEnum 返回枚举对象
     */
    public ResponseInfo(ResultEnum resultEnum) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
    }

    /**
     * 接收枚举信息，且能接收数据的方法
     * @param resultEnum 返回枚举对象
     * @param data       返回数据
     */
    public ResponseInfo(ResultEnum resultEnum, Object data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 返回成功信息
     * @param result 返回的结果信息，一般指获取的资源
     */
    public void setSuccess(Object result) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = result;
    }

    /**
     * 返回错误信息
     * @param code    错误码
     * @param message 错误消息
     */
    public void setError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回错误信息
     * @param exceptionEnum 错误 Enum 枚举
     */
    public void setError(ResultEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

}
