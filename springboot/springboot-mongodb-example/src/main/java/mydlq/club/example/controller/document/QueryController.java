package mydlq.club.example.controller.document;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.document.QueryService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "文档操作-查询文档")
@RestController
@RequestMapping("/document")
public class QueryController {

    @Resource
    private QueryService queryService;

    @ApiOperation(value = "根据【文档ID】查询集合中文档数据",
            notes = "根据【文档ID】查询集合中文档数据。")
    @GetMapping("/query/id")
    public Object getDocById() {
        return queryService.findById();
    }

    @ApiOperation(value = "根据【条件】查询集合中【符合条件】的文档，只取【第一条】数据",
            notes = "根据【条件】查询集合中【符合条件】的文档，只取【第一条】数据。")
    @GetMapping("/query/one")
    public Object getDocByFindOne() {
        return queryService.findOne();
    }

    @ApiOperation(value = "查询集合中的【全部】文档数据",
                  notes = "查询集合中的【全部】文档数据。")
    @GetMapping("/query/all")
    public Object getDocAll() {
        return queryService.findAll();
    }

    @ApiOperation(value = "根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】",
                  notes = "根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】。")
    @GetMapping("/query/field")
    public Object getDocListByField() {
        return queryService.findByCondition();
    }

    @ApiOperation(value = "根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】并【排序】",
                  notes = "根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】并【排序】。")
    @GetMapping("/query/sort")
    public Object getDocListByFieldAndSort() {
        return queryService.findByConditionAndSort();
    }

    @ApiOperation(value = "根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【限制指定数目】",
                  notes = "根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【限制指定数目】。")
    @GetMapping("/query/list/limit")
    public Object getDocListByFieldAndLimit() {
        return queryService.findByConditionAndSortLimit();
    }

    @ApiOperation(value = "根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【并跳过指定数目】",
                  notes = "根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【并跳过指定数目】。")
    @GetMapping("/query/skip")
    public Object getDocListByFieldAndSkip() {
        return queryService.findByConditionAndSortSkip();
    }

    @ApiOperation(value = "查询【存在指定字段名称】的文档数据",
                  notes = "查询【存在指定字段名称】的文档数据。")
    @GetMapping("/query/exist")
    public Object getDocListByExistsField() {
        return queryService.findByExistsField();
    }

    @ApiOperation(value = "根据【AND】关联多个查询条件，查询集合中的文档数据",
                  notes = "根据【AND】关联多个查询条件，查询集合中的文档数据。")
    @GetMapping("/query/and")
    public Object getDocListByAndCondition() {
        return queryService.findByAndCondition();
    }

    @ApiOperation(value = "根据【OR】关联多个查询条件，查询集合中的文档数据",
                  notes = "根据【OR】关联多个查询条件，查询集合中的文档数据。")
    @GetMapping("/query/or")
    public Object getDocListByOrCondition() {
        return queryService.findByOrCondition();
    }

    @ApiOperation(value = "根据【IN】关联多个查询条件，查询集合中的文档数据",
                  notes = "根据【IN】关联多个查询条件，查询集合中的文档数据。")
    @GetMapping("/query/in")
    public Object getDocListByInCondition() {
        return queryService.findByInCondition();
    }

    @ApiOperation(value = "根据【逻辑运算符】查询集合中的文档数据",
                  notes = "根据【逻辑运算符】查询集合中的文档数据。")
    @GetMapping("/query/operator")
    public Object getDocListByOperator() {
        return queryService.findByOperator();
    }

    @ApiOperation(value = "根据【正则表达式】查询集合中的文档数据",
                  notes = "根据【正则表达式】查询集合中的文档数据。")
    @GetMapping("/query/regex")
    public Object getDocListByRegex() {
        return queryService.findByRegex();
    }

    @ApiOperation(value = "统计集合中符合【查询条件】的文档【数量】",
                  notes = "统计集合中符合【查询条件】的文档【数量】。")
    @GetMapping("/query/count")
    public Object getDocListByCount() {
        return queryService.countNumber();
    }

}