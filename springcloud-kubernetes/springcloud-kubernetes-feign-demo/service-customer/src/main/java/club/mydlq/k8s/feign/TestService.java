package club.mydlq.k8s.feign;

import club.mydlq.feign.TestInterface;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(name = "http://192.168.2.11:32025", url = "http://192.168.2.11:32025", fallback = UserFallback.class)
//@FeignClient(name = "http://localhost:8082", url = "http://localhost:8082", fallback = UserFallback.class)
@FeignClient(name = "http://service-provider:8080", url = "http://service-provider:8080", fallback = TestFallback.class)
public interface TestService extends TestInterface {

}
