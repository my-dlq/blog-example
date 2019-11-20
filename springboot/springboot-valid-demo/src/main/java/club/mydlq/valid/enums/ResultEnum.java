package club.mydlq.valid.enums;

public enum ResultEnum {

    SUCCESS(1000, "请求成功"),
    PARAMETER_ERROR(1001, "请求参数有误!"),
    UNKNOWN_ERROR(9999, "未知的错误!");

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

