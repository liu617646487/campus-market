package com.ljc.market.dao.common;

import com.ljc.market.entity.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 学生信息操作dao类
 */
@Repository
public interface StudentDao extends JpaRepository<Student,Long> {

    /**
     * 根据学号查
     * @param sno
     * @return
     */
    Student findBySno(String sno);

    /**
     * 根据id查
     * @param id
     * @return
     */
    @Query("select s from Student s where s.id = :id")
    Student find(@Param("id") Long id);

}
