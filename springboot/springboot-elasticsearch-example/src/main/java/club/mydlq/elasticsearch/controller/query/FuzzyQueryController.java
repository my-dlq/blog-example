package club.mydlq.elasticsearch.controller.query;

import club.mydlq.elasticsearch.service.query.FuzzyQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "查询-模糊查询")
@RestController
@RequestMapping("/query")
public class FuzzyQueryController {

    @Autowired
    private FuzzyQueryService fuzzyQueryService;

    @ApiOperation(value = "模糊查询", notes = "模糊查询")
    @PostMapping("/fuzzy")
    public Object fuzzyQuery() {
        return fuzzyQueryService.fuzzyQuery();
    }

}
