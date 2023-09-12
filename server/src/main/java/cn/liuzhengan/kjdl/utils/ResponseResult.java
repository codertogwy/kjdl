package cn.liuzhengan.kjdl.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseResult {

    private Integer code;
    private String message;
    private Object data;

    public static ResponseResult success() {
        return success(null);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

}
