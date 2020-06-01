package mydlq.club.example.controller.collection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.collection.ViewService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "集合操作-视图操作")
@RestController
@RequestMapping("/view")
public class ViewController {

    @Resource
    private ViewService viewService;

    @ApiOperation(value = "创建【集合视图】", notes = "创建【视图】。")
    @GetMapping
    public Object createView() {
        return viewService.createView();
    }

    @ApiOperation(value = "删除【集合视图】", notes = "删除【视图】。")
    @DeleteMapping
    public Object dropView() {
        return viewService.dropView();
    }

}
