package club.mydlq.valid.entity;

import club.mydlq.valid.enums.ResultEnum;
import lombok.Data;

@Data
public class ResponseResult {
    private Integer code;
    private String msg;

    public ResponseResult(){
    }

    public ResponseResult(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
