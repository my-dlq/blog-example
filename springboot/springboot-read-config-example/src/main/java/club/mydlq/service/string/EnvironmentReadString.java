package club.mydlq.service.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 从环境对象 Environment 中读取 String 配置
 */
@Service
public class EnvironmentReadString {

    @Autowired
    private Environment environment;

    public String readString(){
        String name = environment.getProperty("my1.name", "111");
        String sex = environment.getProperty("my1.sex", "");
        String age = environment.getProperty("my1.age", "18");
        return name + "," + sex + "," + age;
    }

}
