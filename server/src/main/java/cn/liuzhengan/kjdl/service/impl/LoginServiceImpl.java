package cn.liuzhengan.kjdl.service.impl;

import cn.liuzhengan.kjdl.entity.User;
import cn.liuzhengan.kjdl.service.LoginService;
import cn.liuzhengan.kjdl.utils.JwtUtils;
import cn.liuzhengan.kjdl.utils.ResponseResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {


    private final AuthenticationManager authenticationManager;

    public LoginServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public ResponseResult login(User user) {
        Map<String, Object> map = new HashMap<>();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            map.put("status", false);
            map.put("message", e.getMessage());
            return ResponseResult.success(map);
        }
        map.put("status", true);
        User currentUser = (User) authentication.getPrincipal();
        map.put("token", JwtUtils.generateToken(currentUser.getId()));
        map.put("name", currentUser.getName());
        return ResponseResult.success(map);
    }

}
