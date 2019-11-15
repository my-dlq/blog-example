package club.mydlq.k8s.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/service")
    public List<String> getServiceList(){
        return discoveryClient.getServices();
    }

    @GetMapping("/instance")
    public Object getInstance(@RequestParam("name") String name){
        return discoveryClient.getInstances(name);
    }

}
