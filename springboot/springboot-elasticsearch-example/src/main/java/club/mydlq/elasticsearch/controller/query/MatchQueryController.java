package club.mydlq.elasticsearch.controller.query;

import club.mydlq.elasticsearch.service.query.MatchQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "查询-匹配查询")
@RestController
@RequestMapping("/query")
public class MatchQueryController {

    @Autowired
    private MatchQueryService matchQueryService;

    @ApiOperation(value = "匹配查询全部数据", notes = "匹配查询全部数据")
    @PostMapping("/matchAll")
    public Object matchQueryAll() {
        return matchQueryService.matchAllQuery();
    }

    @ApiOperation(value = "匹配查询", notes = "匹配查询数据")
    @PostMapping("/match")
    public Object matchQuery() {
        return matchQueryService.matchQuery();
    }

    @ApiOperation(value = "词语匹配查询", notes = "词语匹配查询")
    @PostMapping("/matchPhrase")
    public Object matchPhraseQuery() {
        return matchQueryService.matchPhraseQuery();
    }

    @ApiOperation(value = "多字段匹配查询", notes = "多字段匹配查询")
    @PostMapping("/matchMulti")
    public Object matchMultiQuery() {
        return matchQueryService.matchMultiQuery();
    }

}
