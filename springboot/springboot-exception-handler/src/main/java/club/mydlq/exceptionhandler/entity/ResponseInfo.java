package club.mydlq.exceptionhandler.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mydlq
 */
@Data
@Accessors(chain = true)
public class ResponseInfo {

    /** 错误码 */
    private Integer code;
    /** 错误信息 */
    private String message = "";
    /** 返回结果 */
    private Object data = "";

}
