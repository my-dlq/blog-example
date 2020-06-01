package mydlq.club.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.index.QueryIndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "索引操作-查询索引")
@RestController
@RequestMapping("/index")
public class QueryIndexController {

    @Resource
    private QueryIndexService queryIndexService;

    @ApiOperation(value = "获取【集合】中【全部】索引", notes = "获取【集合】中【全部】索引。")
    @GetMapping("/query/findAll")
    public Object getIndexAll() {
        return queryIndexService.getIndexAll();
    }

}
