package club.mydlq.operlog.syslog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

/**
 * 请求参数处理工具
 *
 * @author mydlq
 */
@Slf4j
public class RequestParamUtil {

    private RequestParamUtil() {
    }

    /**
     * 处理请求参数 (根据不同的MediaType类型，对不同的参数进行处理)
     *
     * @param request HttpServletRequest
     * @param objectMapper JSON转换工具
     * @param proceedingJoinPoint 连接点
     * @return 处理后的请求参数
     */
    public static String requestParamHandle(HttpServletRequest request,
                                            ObjectMapper objectMapper,
                                            ProceedingJoinPoint proceedingJoinPoint) {
        // 获取 RequestMethod 和 ContentType
        String contentType = request.getContentType();

        // 如果 ContentType 为空，则可能是 URL 传参，直接反 URL 参数
        if (!StringUtils.hasText(contentType)) {
            return request.getQueryString();
        }

        // 获取 RequestMethod 和 ContentType 的枚举
        MediaType mediaType = MediaType.valueOf(contentType);

        // 处理 FROM 表单媒体类型
        if (MediaType.APPLICATION_FORM_URLENCODED.equals(mediaType)) {
            return fromParamHandle(request, objectMapper);
        }
        // 处理 JSON 表单媒体类型
        if (MediaType.APPLICATION_JSON.equals(mediaType)) {
            Object[] args = proceedingJoinPoint.getArgs();
            return (args != null && args.length > 0) ? argsArrayToString(args, objectMapper) : "";
        }

        // 如果以上条件都不符合，则默认反回空串
        return "";
    }

    /**
     * FORM 表单参数处理
     *
     * @param request      请求对象
     * @param objectMapper JSON转换工具
     * @return 转换后的 JSON 字符串
     */
    private static String fromParamHandle(HttpServletRequest request, ObjectMapper objectMapper) {
        try {
            Map<String, String[]> params = request.getParameterMap();
            return objectMapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            log.error("", e);
        }
        return "";
    }

    /**
     * JSON 参数处理
     */
    private static String argsArrayToString(Object[] paramsArray, ObjectMapper objectMapper) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                // 过滤的对象，如上传这种接口接收的参数类型需要过滤掉
                boolean isMultipartFile = o instanceof MultipartFile;
                boolean isRequest = o instanceof HttpServletRequest;
                boolean isResponse = o instanceof HttpServletResponse;
                // 执行过滤
                if (isMultipartFile || isRequest || isResponse) {
                    continue;
                }
                // 过滤完符合条件的转换为 JSON 字符串存储
                try {
                    String jsonObj = objectMapper.writeValueAsString(o);
                    params.append(jsonObj).append(" ");
                } catch (JsonProcessingException e) {
                    log.error("", e);
                }
            }
        }
        return params.toString().trim();
    }

}
