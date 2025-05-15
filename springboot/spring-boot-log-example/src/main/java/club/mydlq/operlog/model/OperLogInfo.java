package club.mydlq.operlog.model;

import lombok.Data;
import java.util.Date;

/**
 * 日志类-记录用户操作行为
 *
 * @author mydlq
 */
@Data
public class OperLogInfo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 系统模块
     */
    private String module;
    /**
     * 操作描述
     */
    private String operDesc;
    /**
     * 请求IP
     */
    private String requestIp;
    /**
     * URI
     */
    private String requestUri;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 响应结果
     */
    private String responseResult;
    /**
     * 操作类型
     */
    private Integer operType;
    /**
     * 操作状态
     */
    private Integer operStatus;
    /**
     * 错误信息
     */
    private String errorInfo;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作时间
     */
    private Date operTime;
}