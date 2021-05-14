package club.mydlq.feign;

import org.springframework.web.bind.annotation.*;

public interface TestInterface {

    /**
     * 获取测试信息
     * @return
     */
    @GetMapping("/")
    public String getInfo();

}
