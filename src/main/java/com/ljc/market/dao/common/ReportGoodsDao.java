package com.ljc.market.dao.common;

import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.ReportGoods;
import com.ljc.market.entity.common.Student;
import com.ljc.market.entity.common.WantedGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.rmi.StubNotFoundException;
import java.util.List;


/**
 * 举报商品Dao层
 */
@Repository
public interface ReportGoodsDao extends JpaRepository<ReportGoods,Long>, JpaSpecificationExecutor<ReportGoods> {


    /**
     * 根据举报商品id查找举报信息
     * @param id
     * @return
     */
    @Query("select rg from ReportGoods rg where rg.id=:id ")
    ReportGoods find(@Param("id") Long id);

    /**
     * 根据商品id和学生id查询举报信息
     * @param goodsid
     * @param studentid
     * @return
     */
    @Query("select rg from ReportGoods rg where rg.goods.id = :goodsid and rg.student.id = :studentid ")
    ReportGoods find(@Param("goodsid")Long goodsid,@Param("studentid") Long studentid);


    /**
     * 根据学生查找举报信息
     * @param student
     * @return
     */
    List<ReportGoods> findByStudent(Student student);

    /**
     * 根据商品查找举报的信息
     * @param goods
     * @return
     */
    List<ReportGoods> findByGoods(Goods goods);



}
