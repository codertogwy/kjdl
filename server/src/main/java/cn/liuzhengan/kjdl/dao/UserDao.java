package cn.liuzhengan.kjdl.dao;


import cn.liuzhengan.kjdl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
