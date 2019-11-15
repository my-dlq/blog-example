package club.mydlq.k8s.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// 通过 Feign 调用 “sleuth-service-provider” 服务接口，并且在 Kubernetes 内部，直接通过“服务名+端口号”调用即可
@FeignClient(name = "http://sleuth-service-provider:8080", url = "http://sleuth-service-provider:8080")
//@FeignClient(name = "http://localhost:8080", url = "http://localhost:8080")
public interface ProviderService {

    @GetMapping("/")
    public String getSleuthInfo();

}
