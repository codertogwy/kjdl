package cn.liuzhengan.kjdl.service;


import cn.liuzhengan.kjdl.entity.User;
import cn.liuzhengan.kjdl.utils.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);
}
