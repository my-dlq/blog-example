package mydlq.club.example.controller.document;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.document.UpdateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "文档操作-更新文档")
@RestController
@RequestMapping("/document")
public class UpdateController {

    @Resource
    private UpdateService updateService;

    @ApiOperation(value = "更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】",
            notes = "更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】。")
    @GetMapping("/update/updateOrCreate")
    public Object update() {
        return updateService.update();
    }

    @ApiOperation(value = "更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】",
            notes = "更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】。")
    @GetMapping("/update/one")
    public Object updateFirst() {
        return updateService.updateFirst();
    }

    @ApiOperation(value = "更新【匹配查询】到的【文档数据集合】中的【所有数据】",
            notes = "更新【匹配查询】到的【文档数据集合】中的【所有数据】。")
    @GetMapping("/update/many")
    public Object updateMany() {
        return updateService.updateMany();
    }

}