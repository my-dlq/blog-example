package club.mydlq.exceptionhandler.enums;

/**
 * 定义返回码的枚举类
 */
public enum ResultEnum {

    /** 成功 */
    SUCCESS(1000, "成功"),
    /** 无法找到资源错误 */
    NOT_FOUNT_RESOURCE(1001,"没有找到相关资源!"),
    /** 请求参数有误 */
    PARAMETER_ERROR(1002,"请求参数有误!"),
    /** 确少必要请求参数异常 */
    PARAMETER_MISSING_ERROR(1003,"确少必要请求参数!"),
    /** 确少必要请求参数异常 */
    REQUEST_MISSING_BODY_ERROR(1004,"缺少请求体!"),
    /** 未知错误 */
    SYSTEM_ERROR(9998,"未知的错误!"),
    /** 系统错误 */
    UNKNOWN_ERROR(9999,"未知的错误!");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
