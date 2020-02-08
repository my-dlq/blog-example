package club.mydlq.elasticsearch.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TermsQuery {
    private String key;
    private String[] values;
}
