package club.mydlq.elasticsearch.controller.query;

import club.mydlq.elasticsearch.service.query.WildcardQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "查询-通配符查询")
@RestController
@RequestMapping("/query")
public class WildcardQueryController {

    @Autowired
    private WildcardQueryService wildcardQueryService;

    @ApiOperation(value = "通配符查询", notes = "通配符查询")
    @PostMapping("/wildcard")
    public Object wildcardQuery() {
        return wildcardQueryService.wildcardQuery();
    }

}
