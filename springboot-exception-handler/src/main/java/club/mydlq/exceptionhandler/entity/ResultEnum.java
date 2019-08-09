package club.mydlq.exceptionhandler.entity;

public enum ResultEnum {

    // 据操作错误定义
    SUCCESS(1000, "Success"),
    UNKNOWN_ERROR(1001,"未知的错误!"),
    NOT_FOUNT_RESOURCE(1002,"没有找到相关资源!"),
    PARAMETER_ERROR(1003,"请求参数有误!");

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
