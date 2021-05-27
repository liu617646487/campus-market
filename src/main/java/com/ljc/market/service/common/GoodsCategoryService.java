package com.ljc.market.service.common;

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.common.GoodsCategoryDao;
import com.ljc.market.entity.common.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.Table;
import java.util.List;

/**
 * 物品管理Service
 */
@Service
public class GoodsCategoryService {

    @Autowired
    private GoodsCategoryDao goodsCategoryDao;

    /**
     * 物品分类添加/编辑 当id不为空时为编辑
     * @param goodsCategory
     * @return
     */
    public GoodsCategory save(GoodsCategory goodsCategory){
        return goodsCategoryDao.save(goodsCategory);
    }

    /**
     * 获取所有的一级分类
     * @return
     */
    public List<GoodsCategory> findTopCategory(){
        return goodsCategoryDao.findByParentIsNull();
    }

    /**
     * 获取所有的二级分类
     * @return
     */
    public List<GoodsCategory> findSecondCategory(){
        return goodsCategoryDao.findByParentIsNotNull();
    }

    /**
     * 获取指定分类列表列
     * @param pageBean
     * @param goodsCategory
     * @return
     */
    public PageBean<GoodsCategory> findList(PageBean<GoodsCategory> pageBean,GoodsCategory goodsCategory ){
        ExampleMatcher matching = ExampleMatcher.matching();
        matching = matching.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        matching = matching.withIgnorePaths("sort");
        Example<GoodsCategory> example = Example.of(goodsCategory, matching);
        Sort sort = Sort.by(Sort.Direction.ASC, "sort");
        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<GoodsCategory> page = goodsCategoryDao.findAll(example, pageable);
        pageBean.setContent(page.getContent());
        pageBean.setTotal(page.getTotalElements());
        pageBean.setTotalPage(page.getTotalPages());
        return pageBean;
    }

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    public GoodsCategory findById(Long id){
        return goodsCategoryDao.find(id);
    }

    /**
     * 根据name获取分类
     * @param name
     * @return
     */
    public List<GoodsCategory> findByName(String name){
        return goodsCategoryDao.findByName(name);
    }


    /**
     * 根据id删除分类
     * @param id
     */
    public void delete(Long id){
       goodsCategoryDao.deleteById(id);
    }

    /**
     * 查询所有分类
     * @return
     */
    public List<GoodsCategory> findAll(){
        return goodsCategoryDao.findAll();
    }


    /**
     * 获取某个顶级分类下的所有子分类
     * @param goodsCategory
     * @return
     */
    public List<GoodsCategory> findChildren(GoodsCategory goodsCategory){
        return goodsCategoryDao.findByParent(goodsCategory);
    }

}
