package com.ljc.market.dao.common;



import com.ljc.market.entity.common.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物品分类管理Dao层
 */
@Repository
public interface GoodsCategoryDao extends JpaRepository<GoodsCategory,Long> {

    List<GoodsCategory> findByParentIsNull();

    List<GoodsCategory> findByParentIsNotNull();

    @Query("select gc from GoodsCategory gc where gc.id = :id")
    GoodsCategory find(@Param("id") Long id);

    /**
     * 根据父类获取子类
     * @param goodsCategory
     * @return
     */
    public List<GoodsCategory> findByParent(GoodsCategory goodsCategory);

    /**
     * 根据name获取分类列表
     * @param name
     * @return
     */
    List<GoodsCategory> findByName(String name);

}
