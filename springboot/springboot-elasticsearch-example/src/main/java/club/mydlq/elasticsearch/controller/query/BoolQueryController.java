package club.mydlq.elasticsearch.controller.query;

import club.mydlq.elasticsearch.service.query.BoolQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "查询-布尔查询")
@RestController
@RequestMapping("/query")
public class BoolQueryController {

    @Autowired
    private BoolQueryService boolQueryService;

    @ApiOperation(value = "布尔查询", notes = "多个条件组合查询")
    @GetMapping("/bool")
    public Object boolQuery() {
        return boolQueryService.boolQuery();
    }

}
