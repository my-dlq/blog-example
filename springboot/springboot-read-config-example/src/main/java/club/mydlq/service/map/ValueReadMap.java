package club.mydlq.service.map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 通过 @Value 方式读取文件中的 Map 数据
 */
@Service
public class ValueReadMap {

    @Value("#{${my3.map}}")
    private Map<String, String> map;

    public String readMap(){
        return map.get("name") + "," + map.get("sex") + "," + map.get("age");
    }

}
