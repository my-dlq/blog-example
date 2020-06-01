package mydlq.club.example.controller.collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.collection.RemoveCollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "集合操作-删除集合")
@RestController
@RequestMapping("/collection")
public class RemoveCollectionController {

    @Resource
    private RemoveCollectionService removeCollectionService;

    @ApiOperation(value = "删除【集合】", notes = "删除【集合结果】。")
    @DeleteMapping("/drop")
    public Object dropCollection() {
        return removeCollectionService.dropCollection();
    }

}
