package cn.liuzhengan.kjdl.service.impl;

import cn.liuzhengan.kjdl.dao.UserDao;
import cn.liuzhengan.kjdl.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
