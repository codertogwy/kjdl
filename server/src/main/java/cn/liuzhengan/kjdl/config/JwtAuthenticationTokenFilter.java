package cn.liuzhengan.kjdl.config;

import cn.liuzhengan.kjdl.dao.UserDao;
import cn.liuzhengan.kjdl.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final UserDao userDao;

    public JwtAuthenticationTokenFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        Integer userId;
        try {
            userId = JwtUtils.verifyToken(token);
        } catch (RuntimeException e) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDao.findById(userId), null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

}
