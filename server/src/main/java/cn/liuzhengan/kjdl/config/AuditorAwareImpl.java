package cn.liuzhengan.kjdl.config;

import cn.liuzhengan.kjdl.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorAwareImpl implements AuditorAware {


    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User currentUser = ((Optional<User>) authentication.getPrincipal()).orElse(null);
            if (currentUser != null) {
                return Optional.of(currentUser.getId());
            }
        }
        return Optional.empty();
    }
}
