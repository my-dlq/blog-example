package mydlq.club.example.controller.document;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.document.RemoveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "文档操作-移除文档")
@RestController
@RequestMapping("/document")
public class RemoveController {

    @Resource
    private RemoveService removeService;

    @ApiOperation(value = "根据设置的【用户查询条件】进行【匹配】，然后删除【匹配的文档】信息",
            notes = "删除【符合条件】的【一个或多个】文档。")
    @GetMapping("/remove")
    public Object remove() {
        return removeService.remove();
    }

    @ApiOperation(value = "根据设置的【用户查询条件】进行【匹配】，然后删除匹配的【第一条文档】信息",
            notes = "删除【符合条件】的【单个文档】，并返回删除的文档。")
    @GetMapping("/remove/one")
    public Object findAndRemove() {
        return removeService.findAndRemove();
    }

    @ApiOperation(value = "根据设置的【用户查询条件】进行【匹配】，然后删除匹配的【全部文档】信息",
            notes = "删除【符合条件】的【全部文档】，并返回删除的文档。")
    @GetMapping("/remove/many")
    public Object findAllAndRemove() {
        return removeService.findAllAndRemove();
    }

}
