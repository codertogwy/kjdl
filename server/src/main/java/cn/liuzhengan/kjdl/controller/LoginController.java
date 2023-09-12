package cn.liuzhengan.kjdl.controller;

import cn.liuzhengan.kjdl.entity.User;
import cn.liuzhengan.kjdl.service.LoginService;
import cn.liuzhengan.kjdl.utils.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

}
