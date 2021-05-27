package com.ljc.market.dao.admin;

import com.ljc.market.entity.admin.Role;
import com.ljc.market.entity.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户数据库处理层
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

    public User findByUsername(String username);

    @Query("select u from User u where u.id = :id")
    User find(@Param("id") Long id);

}
