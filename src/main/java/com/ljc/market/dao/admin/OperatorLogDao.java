package com.ljc.market.dao.admin;

/**
 * 后台操作日志类数据库操作层
 */

import com.ljc.market.entity.admin.OperatorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorLogDao extends JpaRepository<OperatorLog,Long> {

    /**
     * 根据id查找操作日志
     * @param id
     * @return
     */
    @Query("select lo from OperatorLog lo where lo.id = :id")
    OperatorLog find(@Param("id")Long id);

    /**
     * 获取最近的指定条数的操作日志
     * @param size
     * @return
     */
    @Query(value = "select * from admin_operator_log as ol order by ol.create_time desc limit 0,:size",nativeQuery = true)
    List<OperatorLog> findLastestLog(@Param("size")int size);
}
