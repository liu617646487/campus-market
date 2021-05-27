package com.ljc.market.dao.common;

import com.ljc.market.entity.common.Comment;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.ReportGoods;
import com.ljc.market.entity.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论信息Dao层
 */
@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
    
    /**
     * 根据评论信息id查找评论信息
     * @param id
     * @return
     */
    @Query("select c from Comment c where c.id=:id ")
    Comment find(@Param("id") Long id);

    /**
     * 根据商品id和学生id查询评论信息
     * @param goodsid
     * @param studentid
     * @return
     */
    @Query("select c from Comment c where c.goods.id = :goodsid and c.student.id = :studentid ")
    Comment find(@Param("goodsid") Long goodsid, @Param("studentid") Long studentid);


    /**
     * 根据学生查找评论信息
     * @param student
     * @return
     */
    List<Comment> findByStudent(Student student);

    /**
     * 根据商品查找评论的信息
     * @param goods
     * @return
     */
    List<Comment> findByGoods(Goods goods);



}
