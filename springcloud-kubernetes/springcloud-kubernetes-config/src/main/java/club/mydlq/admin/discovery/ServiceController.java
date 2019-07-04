package club.mydlq.admin.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/discovery")
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/service")
    public List<String> getServiceList(){
        return discoveryClient.getServices();
    }

}
