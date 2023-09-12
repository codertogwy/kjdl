package cn.liuzhengan.kjdl.controller;


import cn.liuzhengan.kjdl.utils.ResponseResult;

public class BaseController {

    ResponseResult success() {
        return ResponseResult.success();
    }

    ResponseResult success(Object data) {
        return ResponseResult.success(data);
    }

}
