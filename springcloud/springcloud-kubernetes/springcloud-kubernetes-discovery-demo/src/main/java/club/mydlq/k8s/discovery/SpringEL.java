package club.mydlq.k8s.discovery;

import io.fabric8.kubernetes.api.model.*;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试 SpringEL 表达式
 */
public class SpringEL {

    /**
     * 创建模拟的 Service 对象，和 SpringCloud Kubernetes 服务发现实例保持一致
     * @return
     */
    static Service createService(){
        /** 设置Status **/
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setLoadBalancer(new LoadBalancerStatus());
        /** 设置Spec **/
        ServiceSpec serviceSpec = new ServiceSpec();
        serviceSpec.setClusterIP("10.10.1.11");
        // 设置type
        serviceSpec.setType("NodePort");
        // 设置 Selector
        Map selector = new HashMap();
        selector.put("app", "test-project");
        serviceSpec.setSelector(selector);
        // 设置端口1
        ServicePort servicePort1 = new ServicePort();
        servicePort1.setName("server");
        servicePort1.setPort(8080);
        servicePort1.setNodePort(30080);
        servicePort1.setProtocol("TCP");
        servicePort1.setTargetPort(new IntOrString("8080"));
        // 设置端口2
        ServicePort servicePort2 = new ServicePort();
        servicePort2.setName("management  ");
        servicePort2.setPort(8081);
        servicePort2.setNodePort(30081);
        servicePort2.setProtocol("TCP");
        servicePort2.setTargetPort(new IntOrString("8081"));
        // 将两个端口加如Spec
        List<ServicePort> servicePortList = new ArrayList<>();
        servicePortList.add(servicePort1);
        servicePortList.add(servicePort2);
        serviceSpec.setPorts(servicePortList);
        /** 设置 Metadata **/
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName("test-project");
        objectMeta.setNamespace("mydlqcloud");
        // 设置Label
        Map labels = new HashMap();
        labels.put("app", "test-project");
        labels.put("group", "b");
        objectMeta.setLabels(labels);
        objectMeta.setResourceVersion("3373499");
        objectMeta.setCreationTimestamp("2019-06-23T16:24:39Z");
        /** 设置Service **/
        Service service = new Service();
        service.setKind("Service");
        service.setApiVersion("v1");
        service.setStatus(serviceStatus);
        service.setSpec(serviceSpec);
        service.setMetadata(objectMeta);
        return service;
    }

    /**
     * 使用 SpringEL 表达式测试 filter
     * @param object
     * @param springEL
     * @return
     */
    static boolean verification(Object object,String springEL){
        // 创建上下文环境
        SimpleEvaluationContext evalCtxt = SimpleEvaluationContext.forReadOnlyDataBinding().withInstanceMethods().build();
        // 创建SpEL表达式的解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 验证表达式验证内容是否和对象中的值匹配并返回 Boolean 结果
        return parser.parseExpression(springEL).getValue(evalCtxt, object, Boolean.class);
    }

    public static void main(String[] args) {
        // 输入待验证的表达式
        String springEL = "metadata.name == 'test-project'";
        // 创建模拟的 Service 对象
        Service service = createService();
        // 验证 SpringEL 表达式和对象中现有的值是否匹配
        boolean isTrue = verification(service,springEL);
        //输出结果
        System.out.println(isTrue);
    }

}
