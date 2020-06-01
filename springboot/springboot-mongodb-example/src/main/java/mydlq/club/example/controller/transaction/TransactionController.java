package mydlq.club.example.controller.transaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.transaction.TransactionExample;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "事务操作-事务测试")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Resource
    private TransactionExample transactionExample;

    @ApiOperation(value = "事务测试", notes = "事务测试。")
    @PostMapping("/test")
    public Object transactionTest(){
        return transactionExample.transactionTest();
    }

}
