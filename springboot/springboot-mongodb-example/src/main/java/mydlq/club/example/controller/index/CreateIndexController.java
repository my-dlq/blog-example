package mydlq.club.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.index.CreateIndexService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "索引操作-创建索引")
@RestController
@RequestMapping("/index")
public class CreateIndexController {

    @Resource
    private CreateIndexService createIndexService;

    @ApiOperation(value = "创建【升序】索引", notes = "创建【升序】索引。")
    @PostMapping("/create/ascending")
    public Object createAscendingIndex() {
        return createIndexService.createAscendingIndex();
    }

    @ApiOperation(value = "创建【降序】索引", notes = "创建【降序】索引。")
    @PostMapping("/create/descending")
    public Object createDescendingIndex() {
        return createIndexService.createDescendingIndex();
    }

    @ApiOperation(value = "创建【复合】索引", notes = "创建【复合】索引。")
    @PostMapping("/create/composite")
    public Object createCompositeIndex() {
        return createIndexService.createCompositeIndex();
    }

    @ApiOperation(value = "创建【文本】索引", notes = "创建【文本】索引。")
    @PostMapping("/create/text")
    public Object createTextIndex() {
        return createIndexService.createTextIndex();
    }

    @ApiOperation(value = "创建【哈希】索引", notes = "创建【哈希】索引。")
    @PostMapping("/create/hash")
    public Object createHashIndex() {
        return createIndexService.createHashIndex();
    }

    @ApiOperation(value = "创建升序【唯一】索引", notes = "创建升序【唯一】索引。")
    @PostMapping("/create/unique")
    public Object createUniqueIndex() {
        return createIndexService.createUniqueIndex();
    }

    @ApiOperation(value = "创建【局部】索引", notes = "创建【局部】索引。")
    @PostMapping("/create/partial")
    public Object createPartialIndex() {
        return createIndexService.createPartialIndex();
    }

}
