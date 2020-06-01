package mydlq.club.example.controller.collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.collection.QueryCollectionService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "集合操作-查询集合")
@RestController
@RequestMapping("/collection")
public class QueryCollectionController {

    @Resource
    private QueryCollectionService queryCollectionService;

    @ApiOperation(value = "获取【集合名称列表】", notes = "获取【集合名称列表】。")
    @GetMapping("/list")
    public Object getCollectionNameList() {
        return queryCollectionService.getCollectionNames();
    }

    @ApiOperation(value = "获取【集合名称列表】", notes = "获取【集合名称列表】。")
    @GetMapping("/exists")
    public Object getCollectionExists() {
        return queryCollectionService.collectionExists();
    }

}
