package com.ljc.market.dao.common;

import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Student;
import com.ljc.market.entity.common.WantedGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 求购商品Dao层
 */
@Repository
public interface WantedGoodsDao extends JpaRepository<WantedGoods,Long> , JpaSpecificationExecutor<WantedGoods> {

    /**
     * 根据学生查找求购的商品
     * @param student
     * @return
     */
    List<WantedGoods> findByStudent(Student student);

    /**
     * 根据商品id和学生id查找商品
     * @param id
     * @param studentid
     * @return
     */
    @Query("select wg from WantedGoods wg where wg.id=:id and wg.student.id=:studentid")
    WantedGoods find(@Param("id")Long id, @Param("studentid")Long studentid);



}
