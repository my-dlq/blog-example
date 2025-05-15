package club.mydlq.operlog.syslog.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP 地址处理工具
 *
 * @author mydlq
 */
@Slf4j
public class IpUtil {

    private IpUtil() {
    }

    /**
     * 本机IP地址
     */
    private static final String LOCALHOST_IP = "127.0.0.1";
    /**
     * 客户端与服务器同为一台机器，获取的 ip 有时候是 IPV6 格式
     */
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    private static final String SEPARATOR = ",";
    private static final String UNKNOWN = "unknown";
    private static final int MAX_LEN_LIMIT = 15;

    /**
     * 根据 HttpServletRequest 获取 IP
     *
     * @param request 请求对象
     * @return 请求IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (isValid(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isValid(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (isValid(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isValid(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (isValid(ip)) {
            ip = request.getRemoteAddr();
        }
        if (LOCALHOST_IP.equalsIgnoreCase(ip) || LOCALHOST_IPV6.equalsIgnoreCase(ip)) {
            try {
                // 根据网卡取本机配置的 IP
                InetAddress iNet = InetAddress.getLocalHost();
                if (iNet != null) {
                    ip = iNet.getHostAddress();
                }
            } catch (UnknownHostException e) {
                log.error("", e);
            }
        }

        // 对于通过多个代理的情况，分割出第一个 IP
        if (ip != null && ip.length() > MAX_LEN_LIMIT && ip.contains(SEPARATOR)) {
            ip = ip.substring(0, ip.indexOf(SEPARATOR));
        }
        return LOCALHOST_IPV6.equals(ip) ? LOCALHOST_IP : ip;
    }

    /**
     * 判断 IP 是否有效
     *
     * @param ip IP地址
     * @return 是否有效
     */
    private static boolean isValid(String ip) {
        return !StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip);
    }

}