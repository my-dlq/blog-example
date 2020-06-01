package mydlq.club.example.controller.aggregate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.aggregate.AggregateGroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "聚合操作-聚合表达式")
@RestController
@RequestMapping("/aggregate")
public class AggregateGroupController {

    @Resource
    private AggregateGroupService aggregateService;

    @ApiOperation(value = "使用管道操作符【$group】结合【$count】方法进行聚合统计",
            notes = "使用管道操作符【$group】结合【$count】方法进行聚合统计。")
    @GetMapping("/group/count")
    public Object aggregationGroupCount() {
        return aggregateService.aggregationGroupCount();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $max 进行聚合统计",
            notes = "使用管道操作符 $group 结合表达式操作符 $max 进行聚合统计。")
    @GetMapping("/group/max")
    public Object aggregationGroupMax() {
        return aggregateService.aggregationGroupMax();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $min 进行聚合统计",
            notes = "使用管道操作符 $group 结合表达式操作符 $min 进行聚合统计。")
    @GetMapping("/group/min")
    public Object aggregationGroupMin() {
        return aggregateService.aggregationGroupMin();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $sum 进行聚合统计",
            notes = "使用管道操作符 $group 结合表达式操作符 $sum 进行聚合统计。")
    @GetMapping("/group/sum")
    public Object aggregationGroupSum() {
        return aggregateService.aggregationGroupSum();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $avg 进行聚合统计",
            notes = "使用管道操作符 $group 结合表达式操作符 $avg 进行聚合统计。")
    @GetMapping("/group/avg")
    public Object aggregationGroupAvg() {
        return aggregateService.aggregationGroupAvg();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $first 获取每个组的包含某字段的文档的第一条数据",
            notes = "使用管道操作符 $group 结合表达式操作符 $first 获取每个组的包含某字段的文档的第一条数据。")
    @GetMapping("/group/first")
    public Object aggregationGroupFirst() {
        return aggregateService.aggregationGroupFirst();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $last 获取每个组的包含某字段的文档的最后一条数据",
            notes = "使用管道操作符 $group 结合表达式操作符 $last 获取每个组的包含某字段的文档的最后一条数据。")
    @GetMapping("/group/last")
    public Object aggregationGroupLast() {
        return aggregateService.aggregationGroupLast();
    }

    @ApiOperation(value = "使用管道操作符 $group 结合表达式操作符 $push 获取某字段列表",
            notes = "使用管道操作符 $group 结合表达式操作符 $push 获取某字段列表。")
    @GetMapping("/group/push")
    public Object aggregationGroupPush() {
        return aggregateService.aggregationGroupPush();
    }

}
