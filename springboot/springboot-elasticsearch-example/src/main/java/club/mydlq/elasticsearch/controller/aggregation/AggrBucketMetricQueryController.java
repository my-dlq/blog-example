package club.mydlq.elasticsearch.controller.aggregation;

import club.mydlq.elasticsearch.service.aggregation.AggrBucketMetricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "聚合—分桶后再聚合分析")
@RestController
@RequestMapping("/query")
public class AggrBucketMetricQueryController {

    @Autowired
    private AggrBucketMetricService aggrBucketMetricService;

    @ApiOperation(value = "分桶后查询", notes = "先根据 term 分桶，然后获取分桶后排序的前几个")
    @PostMapping("/bucketMetric")
    public Object termQuery() {
        return aggrBucketMetricService.aggregationTopHits();
    }

}
