package club.mydlq.service.list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 通过 @Value 方式读取文件中的 List 数据
 */
@Service
public class ValueReadList {

    @Value("#{'${my5.list}'.split(',')}")
    private List<String> list;

    public String readList() {
        StringBuilder builder = new StringBuilder();
        for (String str:list){
            builder.append(str).append(",");
        }
        // 移除最后的“，”号
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }

}
