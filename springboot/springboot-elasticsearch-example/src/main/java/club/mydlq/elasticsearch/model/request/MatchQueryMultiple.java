package club.mydlq.elasticsearch.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MatchQueryMultiple {
    private String value;
    private String[] keys;
}
