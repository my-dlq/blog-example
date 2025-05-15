package club.mydlq.operlog.model;

import lombok.Data;
import lombok.ToString;

/**
 * 查询条件
 *
 * @author mydlq
 */
@Data
@ToString
public class QueryParam {
    /**
     * 参数1
     */
    private String param1;
    /**
     * 参数2
     */
    private String param2;
}
