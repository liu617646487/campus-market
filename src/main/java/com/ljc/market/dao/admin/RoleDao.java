package com.ljc.market.dao.admin;

import com.ljc.market.entity.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {

    /**
     * 根据id查找角色
     * @param id
     * @return
     */
    @Query("select r from Role r where r.id = :id")
    Role find(@Param("id") Long id);

}
