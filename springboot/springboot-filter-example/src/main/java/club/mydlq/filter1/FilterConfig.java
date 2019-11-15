package club.mydlq.filter1;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     * 代码方式注册Bean
     */
    @Bean
    public FilterRegistrationBean customFilter(){
        FilterRegistrationBean<MyCustomFilter1> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new MyCustomFilter1());
        filterBean.setName("FilterController");
        filterBean.addUrlPatterns("/cc/*","/bb/*");
        return filterBean;
    }

}
