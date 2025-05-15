package club.mydlq.operlog.syslog.aop;

import club.mydlq.operlog.model.OperLogInfo;
import club.mydlq.operlog.syslog.enums.OperStatusEnum;
import club.mydlq.operlog.service.OperLogService;
import club.mydlq.operlog.syslog.annotation.OperLog;
import club.mydlq.operlog.syslog.utils.IpUtil;
import club.mydlq.operlog.syslog.utils.RequestParamUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志 AOP 切面
 *
 * @author mydlq
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    private final HttpServletRequest request;
    private final OperLogService operLogService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SystemLogAspect(HttpServletRequest request,
                           ObjectMapper objectMapper,
                           OperLogService operLogService) {
        this.request = request;
        this.objectMapper = objectMapper;
        this.operLogService = operLogService;
    }

    /**
     * 根据 @SystemControllerLog 主键进行切
     */
    @Pointcut("@annotation(club.mydlq.operlog.syslog.annotation.OperLog)")
    public void controllerAspect() {
    }

    /**
     * 环绕 Advice - 用于拦截 Controller 层设添加了 @OperLog 注解的方法
     *
     * @param proceedingJoinPoint 切点
     */
    @Around("controllerAspect()")
    public Object doAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 创建接收响应结果的对象、操作日志对象
        Object responseResult;
        OperLogInfo operLogInfo = new OperLogInfo();
        try {
            // --- 前置处理: 拼接操作日志信息 ---
            setOperLog(operLogInfo, proceedingJoinPoint);

            // 使前置 Advice 通过并获取响应结果
            responseResult = proceedingJoinPoint.proceed();

            // --- 后置处理: 设置响应结果、操作状态 ---
            // 将响应结果转换为JSON串，并且设置操作状态为成功
            operLogInfo.setResponseResult(responseResult == null ? "" : objectMapper.writeValueAsString(responseResult));
            operLogInfo.setOperStatus(OperStatusEnum.SUCCESS.getCode());

            // 返回响应结果
            return responseResult;
        } catch (Throwable e) {
            // 设置操作状态为失败，并且记录错误信息
            operLogInfo.setOperStatus(OperStatusEnum.FAIL.getCode());
            operLogInfo.setErrorInfo(e.toString());
            throw e;
        } finally {
            // 记录操作日志到数据库
            operLogService.saveLog(operLogInfo);
        }
    }

    /**
     * 获取操作日志数据
     *
     * @param operLogInfo             操作日志对象
     * @param proceedingJoinPoint 连接点
     */
    private void setOperLog(OperLogInfo operLogInfo, ProceedingJoinPoint proceedingJoinPoint) {
        // 获取注解信息
        OperLog annotationLog = getAnnotationLog(proceedingJoinPoint);
        if (annotationLog == null) {
            return;
        }
        // 设置请求参数
        setRequestParamValue(operLogInfo, proceedingJoinPoint);
        // 设置操作描述
        operLogInfo.setOperDesc(annotationLog.description());
        // 设置操作模块
        operLogInfo.setModule(annotationLog.module());
        // 设置操作类型
        operLogInfo.setOperType(annotationLog.operType().getCode());
        // 设置操作人
        operLogInfo.setOperator(this.getOperator());
        // 设置请求的IP
        operLogInfo.setRequestIp(IpUtil.getIpAddress(request));
        // 设置操作时间
        operLogInfo.setOperTime(new Date());
        // 请求的方法类型(get/post/put)
        operLogInfo.setRequestMethod(request.getMethod());
        // 设置请求地址
        operLogInfo.setRequestUri(request.getRequestURI());
    }

    /**
     * 获取操作人 (这里应当根据鉴权系统获取用户信息，然后填入操作用户)
     *
     * @return 操作人
     */
    private String getOperator() {
        // 设置一个假用户名test
        return "test";
    }

    /**
     * 获取日志注解
     *
     * @param proceedingJoinPoint 切入点
     * @return 执行结果
     */
    private OperLog getAnnotationLog(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(OperLog.class);
        }
        return null;
    }

    /**
     * 设置请求参数，放到 operLog 对象中
     * (注意请求参数可能涉及到数据安全问题，所以这里可以根据业务场景决定，是否进行数据脱敏或者加密处理)
     *
     * @param operLogInfo             操作日志对象
     * @param proceedingJoinPoint 切点
     */
    private void setRequestParamValue(OperLogInfo operLogInfo, ProceedingJoinPoint proceedingJoinPoint) {
        // 处理请求参数
        String requestParam = RequestParamUtil.requestParamHandle(request, objectMapper, proceedingJoinPoint);
        // 将请求参数添加到操作日志对象
        operLogInfo.setRequestParam(requestParam);
    }

}
