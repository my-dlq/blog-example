package club.mydlq.operlog.syslog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作状态枚举
 *
 * @author mydlq
 */
@Getter
@AllArgsConstructor
public enum OperStatusEnum {
    /**
     * 失败
     */
    FAIL(0, "失败"),
    /**
     * 成功
     */
    SUCCESS(1, "成功"),
    ;

    /**
     * 状态编号
     */
    private final int code;
    /**
     * 状态描述
     */
    private final String name;
}
