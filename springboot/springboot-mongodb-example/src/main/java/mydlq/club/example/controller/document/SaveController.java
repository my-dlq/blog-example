package mydlq.club.example.controller.document;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.document.SaveService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "文档操作-存储文档")
@RestController
@RequestMapping("/document")
public class SaveController {

    @Resource
    private SaveService saveService;

    @ApiOperation(value = "存储【一条文档】数据，如果文档信息已经【存在就执行更新】",
            notes = "存储【一条文档】数据，如果文档信息已经【存在就执行更新】。")
    @PostMapping("/save/one")
    public Object insertData() {
        return saveService.save();
    }

}
