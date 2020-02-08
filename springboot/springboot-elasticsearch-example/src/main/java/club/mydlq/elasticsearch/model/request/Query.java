package club.mydlq.elasticsearch.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Query {
    private String key;
    private String value;
}
