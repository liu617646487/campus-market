package com.ljc.market.service.common;

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.common.GoodsDao;
import com.ljc.market.dao.common.WantedGoodsDao;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Student;
import com.ljc.market.entity.common.WantedGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 求购商品Service
 */
@Service
public class WantedGoodsService {

    @Autowired
    private WantedGoodsDao wantedGoodsDao;

    /**
     * 求购物品信息添加/编辑
     * @param wantedGoods
     * @return
     */
    public WantedGoods save(WantedGoods wantedGoods){
        return wantedGoodsDao.save(wantedGoods);
    }

    /**
     * 根据学生查询求购物品
     * @param student
     * @return
     */
    public List<WantedGoods> findByStudent(Student student){
        return wantedGoodsDao.findByStudent(student);
    }

    /**
     * 根据商品id和学生id查找商品
     * @param id
     * @param studentid
     * @return
     */
    public WantedGoods find(Long id,Long studentid){
        return wantedGoodsDao.find(id,studentid);
    }

    /**
     * 根据商品id和删除商品
     * @param id
     * @return
     */
    public void delete(Long id){
        wantedGoodsDao.deleteById(id);
    }


    /**
     * 分页展示求购物品列表
     * @param wantedGoods
     * @param pageBean
     * @return
     */
    public PageBean<WantedGoods> findList(PageBean<WantedGoods> pageBean,WantedGoods wantedGoods){
        ExampleMatcher matching = ExampleMatcher.matching();
        matching = matching.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        matching = matching.withIgnorePaths("viewNumber");
        Example<WantedGoods> example = Example.of(wantedGoods, matching);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<WantedGoods> page = wantedGoodsDao.findAll(example, pageable);
        pageBean.setContent(page.getContent());
        pageBean.setTotal(page.getTotalElements());
        pageBean.setTotalPage(page.getTotalPages());
        return pageBean;
    }

    /**
     * 根据不同条件查
     * @param pageBean
     * @param wantedGoods
     * @return
     */
    public PageBean<WantedGoods> findListPro(PageBean<WantedGoods> pageBean,WantedGoods wantedGoods){
        Specification<WantedGoods> specification = new Specification<WantedGoods>() {
            @Override
            public Predicate toPredicate(Root<WantedGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + (wantedGoods.getName() == null ? "" : wantedGoods.getName()) + "%");
                predicate = criteriaBuilder.and(name);
                if(wantedGoods.getStudent()!=null&&wantedGoods.getStudent().getId()!=null){
                    Predicate student = criteriaBuilder.equal(root.get("student"), wantedGoods.getStudent().getId());
                    predicate = criteriaBuilder.and(name, student);
                }

                return predicate;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime","viewNumber");
        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<WantedGoods> page = wantedGoodsDao.findAll(specification, pageable);
        pageBean.setContent(page.getContent());
        pageBean.setTotal(page.getTotalElements());
        pageBean.setTotalPage(page.getTotalPages());
        return pageBean;
    }

}
