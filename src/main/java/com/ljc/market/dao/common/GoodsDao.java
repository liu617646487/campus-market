package com.ljc.market.dao.common;

import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.GoodsCategory;
import com.ljc.market.entity.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物品数据库操作层
 */
@Repository
public interface GoodsDao extends JpaRepository<Goods,Long>, JpaSpecificationExecutor<Goods> {

    /**
     * 根据id查询单个商品
     * @param id
     * @return
     */
    @Query("select g from Goods g where g.id = :id")
    Goods find(@Param("id") Long id);

    /**
     * 根据学生查询你发布的商品
     * @param student
     * @return
     */
    List<Goods> findByStudent(Student student);

    /**
     * 根据学生和商品id查询商品
     * @param id
     * @param studentid
     * @return
     */
    @Query("select g from Goods g where g.id=:id and g.student.id=:studentid")
    Goods find(@Param("id")Long id, @Param("studentid")Long studentid);

    /**
     * 根据物品分类查询物品列表
     * @param cids
     * @param offSet
     * @param pageSize
     * @return
     */
    @Query(value = "select * from home_goods where goods_category_id in :cids and status = 1 order by create_time desc,flag desc,recommend desc limit :offSet,:pageSize",nativeQuery = true)
    List<Goods> findList(@Param("cids")List<Long> cids, @Param("offSet")Integer offSet,@Param("pageSize")Integer pageSize);

    /**
     * 获取根据分类搜索到的总条数
     * @param cids
     * @return
     */
    @Query(value = "select count(*) from home_goods where goods_category_id in :cids and status = 1 ",nativeQuery = true)
    Long getTotalCount(@Param("cids")List<Long> cids);

    /**
     * 获取指定状态得商品总数
     * @param status
     * @return
     */
    @Query("select count(g) from Goods g where g.status = :status")
    Long getTotalCount(@Param("status")Integer status);


    @Query(value = "select * from home_goods where name like %:name%",nativeQuery = true)
    List<Goods> findByName(@Param("name")String name);

}
