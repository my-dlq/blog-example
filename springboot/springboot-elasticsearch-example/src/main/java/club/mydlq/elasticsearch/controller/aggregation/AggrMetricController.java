package club.mydlq.elasticsearch.controller.aggregation;

import club.mydlq.elasticsearch.service.aggregation.AggrMetricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "聚合—聚合分析")
@RestController
@RequestMapping("/aggr")
public class AggrMetricController {

    @Autowired
    private AggrMetricService aggrMetricService;

    @ApiOperation(value = "聚合求平均值", notes = "聚合后求各个数据值的平均值")
    @GetMapping("/avg")
    public Object avgAggrMetric() {
        return aggrMetricService.aggregationAvg();
    }

    @ApiOperation(value = "聚合统计总数", notes = "聚合后求各个数据值的总数")
    @GetMapping("/count")
    public Object countAggrMetric() {
        return aggrMetricService.aggregationCount();
    }

    @ApiOperation(value = "聚合求最大值", notes = "聚合后求各个数据值的最大值")
    @GetMapping("/max")
    public Object maxAggrMetric() {
        return aggrMetricService.aggregationMax();
    }

    @ApiOperation(value = "聚合求最小值", notes = "聚合后求各个数据值的最小值")
    @GetMapping("/min")
    public Object minAggrMetric() {
        return aggrMetricService.aggregationMin();
    }

    @ApiOperation(value = "聚合求百分位", notes = "聚合后求各个数据值的百分位")
    @GetMapping("/percentiles")
    public Object percentilesAggrMetricr() {
        return aggrMetricService.aggregationPercentiles();
    }

    @ApiOperation(value = "聚合求和", notes = "聚合后求各个数据值的和")
    @GetMapping("/sum")
    public Object sumAggrMetric() {
        return aggrMetricService.aggregationSum();
    }

    @ApiOperation(value = "聚合求各个指标", notes = "聚合后求各个数据值的统计(count)、平均(avg)、最大(max)、最小(min)、求和值(sum)的值")
    @GetMapping("/stats")
    public Object statsAggrMetric() {
        return aggrMetricService.aggregationStats();
    }

}
