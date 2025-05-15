package club.mydlq.operlog.syslog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型枚举
 *
 * @author mydlq
 */
@Getter
@AllArgsConstructor
public enum OperTypeEnum {
    /**
     * 查询
     */
    QUERY(0, "查询"),
    /**
     * 新增
     */
    INSERT(1, "新增"),
    /**
     * 修改
     */
    UPDATE(2, "修改"),
    /**
     * 删除
     */
    DELETE(3, "删除"),
    /**
     * 上传
     */
    UPLOAD(4, "上传"),
    /**
     * 下载
     */
    DOWNLOAD(5, "下载"),
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
