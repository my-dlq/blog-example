package mydlq.club.example.controller.command;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.command.RunCommandService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "RunCommand 命令")
@RestController
@RequestMapping("/command")
public class RunCommandController {

    @Resource
    private RunCommandService runCommandService;

    @ApiOperation(value = "执行【runCommand】自定义命令", notes = "执行【runCommand】自定义命令。")
    @GetMapping
    public Object runCommand() {
        return runCommandService.runCommand();
    }

}
