package club.mydlq.elasticsearch.controller.aggregation;

import club.mydlq.elasticsearch.service.aggregation.AggrBucketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "聚合—分桶聚合查询")
@RestController
@RequestMapping("/bucket")
public class AggrBucketController {

    @Autowired
    private AggrBucketService aggrBucketService;

    @ApiOperation(value = "按词分桶", notes = "根据拆分的词不同，将对于的词分到不同的桶中")
    @GetMapping("/terms")
    public Object bucketTerms() {
        return aggrBucketService.aggrBucketTerms();
    }

    @ApiOperation(value = "指定数值范围分桶", notes = "指定一个数值范围，然后按这个数值范围为进行分桶")
    @GetMapping("/range")
    public Object bucketRange() {
        return aggrBucketService.aggrBucketRange();
    }

    @ApiOperation(value = "根据日期范围分桶", notes = "指定一个日期范围，然后按这个日期范围进行分桶")
    @GetMapping("/dateRange")
    public Object bucketDateRange() {
        return aggrBucketService.aggrBucketDateRange();
    }

    @ApiOperation(value = "数值直方图", notes = "根据数值范围输出直方图数据")
    @GetMapping("/histogram")
    public Object bucketHistogram() {
        return aggrBucketService.aggrBucketHistogram();
    }

    @ApiOperation(value = "日期直方图", notes = "根据日期范围输出直方图数据")
    @GetMapping("/dateHistogram")
    public Object bucketDateHistogram() {
        return aggrBucketService.aggrBucketDateHistogram();
    }

}
