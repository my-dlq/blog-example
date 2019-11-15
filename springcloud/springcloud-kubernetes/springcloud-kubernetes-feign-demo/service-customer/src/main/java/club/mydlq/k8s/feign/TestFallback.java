package club.mydlq.k8s.feign;

import org.springframework.stereotype.Component;

@Component
public class TestFallback implements TestService {

    @Override
    public String getInfo() {
        return "fallback!";
    }

}