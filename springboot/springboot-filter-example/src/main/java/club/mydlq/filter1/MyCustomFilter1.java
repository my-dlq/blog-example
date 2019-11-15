package club.mydlq.filter1;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

/**
 * 自定义过滤器（配置 Bean 方式）
 */
public class MyCustomFilter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before ...");
        // 过滤器放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("after ...");
    }

    @Override
    public void destroy() {
        System.err.println("过滤器销毁");
    }

}
