package cn.liuzhengan.kjdl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Entity
public class User extends BaseEntity implements UserDetails {

    //用户名
    @Column(unique = true, nullable = false)
    private String username;
    //密码
    @Column(nullable = false)
    private String password;
    //姓名
    private String name;
    //电话
    private String phone;
    //邮箱
    private String email;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //账户是否没有过期、账户是否没有被锁定、密码是否没有过期、以及账户是否可用。
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
