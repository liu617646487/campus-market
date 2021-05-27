package com.ljc.market.dao.admin;

import com.ljc.market.entity.admin.DatabaseBak;
import com.ljc.market.entity.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据库备份数据库处理层
 */
@Repository
public interface DatabaseBakDao extends JpaRepository<DatabaseBak,Long> {

    @Query("select db from DatabaseBak db where db.id=:id")
    DatabaseBak find(@Param("id") Long id);
}
