package club.mydlq.elasticsearch.controller.query;

import club.mydlq.elasticsearch.service.query.RangeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "查询-范围查询")
@RestController
@RequestMapping("/query")
public class RangeQueryController {

    @Autowired
    private RangeQueryService rangeQueryService;

    @ApiOperation(value = "范围查询", notes = "范围查询")
    @PostMapping("/range")
    public Object rangeQuery() {
        return rangeQueryService.rangeQuery();
    }

    @ApiOperation(value = "时间范围查询", notes = "时间范围查询")
    @PostMapping("/dateRange")
    public Object dateRangeQuery() {
        return rangeQueryService.dateRangeQuery();
    }
}
