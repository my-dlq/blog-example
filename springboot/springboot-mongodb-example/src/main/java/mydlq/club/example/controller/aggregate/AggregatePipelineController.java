package mydlq.club.example.controller.aggregate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.aggregate.AggregatePipelineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "聚合操作-管道操作符")
@RestController
@RequestMapping("/aggregate")
public class AggregatePipelineController {

    @Resource
    private AggregatePipelineService aggregatePipelineService;

    @ApiOperation(value = "使用 $group 和 $match 聚合,先使用 $match 过滤文档，然后再使用 $group 进行分组",
            notes = "使用 $group 和 $match 聚合,先使用 $match 过滤文档，然后再使用 $group 进行分组。")
    @GetMapping("/pipeline/match")
    public Object aggregateGroupMatch() {
        return aggregatePipelineService.aggregateGroupMatch();
    }

    @ApiOperation(value = "使用 $group 和 $sort 聚合,先使用 $group 进行分组，然后再使用 $sort 排序",
            notes = "使用 $group 和 $sort 聚合,先使用 $group 进行分组，然后再使用 $sort 排序。")
    @GetMapping("/pipeline/sort")
    public Object aggregateGroupSort() {
        return aggregatePipelineService.aggregateGroupSort();
    }

    @ApiOperation(value = "使用 $group 和 $limit 聚合,先使用 $group 进行分组，然后再使用 $limit 限制一定数目文档",
            notes = "使用 $group 和 $limit 聚合,先使用 $group 进行分组，然后再使用 $limit 限制一定数目文档。")
    @GetMapping("/pipeline/limit")
    public Object aggregateGroupLimit() {
        return aggregatePipelineService.aggregateGroupLimit();
    }

    @ApiOperation(value = "使用 $group 和 $skip 聚合,先使用 $group 进行分组，然后再使用 $skip 跳过一定数目文档",
            notes = "使用 $group 和 $skip 聚合,先使用 $group 进行分组，然后再使用 $skip 跳过一定数目文档。")
    @GetMapping("/pipeline/skip")
    public Object aggregateGroupSkip() {
        return aggregatePipelineService.aggregateGroupSkip();
    }

    @ApiOperation(value = "使用 $group 和 $project 聚合,先使用 $group 进行分组，然后再使用 $project 限制显示的字段",
            notes = "使用 $group 和 $project 聚合,先使用 $group 进行分组，然后再使用 $project 限制显示的字段。")
    @GetMapping("/pipeline/project")
    public Object aggregateGroupProject() {
        return aggregatePipelineService.aggregateGroupProject();
    }

    @ApiOperation(value = "使用 $group 和 $unwind 聚合,先使用 $group 进行分组，然后再使用 $unwind 拆分文档中的数组为一条新文档记录",
            notes = "使用 $group 和 $unwind 聚合,先使用 $group 进行分组，然后再使用 $unwind 拆分文档中的数组为一条新文档记录。")
    @GetMapping("/pipeline/unwind")
    public Object aggregateProjectUnwind() {
        return aggregatePipelineService.aggregateProjectUnwind();
    }

}
