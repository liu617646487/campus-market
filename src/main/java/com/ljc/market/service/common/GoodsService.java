package com.ljc.market.service.common;

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.common.GoodsDao;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Student;
import org.aspectj.apache.bcel.generic.RET;
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
 * 物品管理Service
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 物品商品添加/编辑 当id不为空时为编辑
     * @param goods
     * @return
     */
    public Goods save(Goods goods){
        return goodsDao.save(goods);
    }


    /**
     * 获取指定商品列表列
     * @param pageBean
     * @param goods
     * @return
     */
    public PageBean<Goods> findList(PageBean<Goods> pageBean,Goods goods){
        ExampleMatcher matching = ExampleMatcher.matching();
        matching = matching.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        matching = matching.withIgnorePaths("flag","recommend","viewNumber");
        Example<Goods> example = Example.of(goods, matching);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime","recommend","flag","viewNumber");
        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<Goods> page = goodsDao.findAll(example, pageable);
        pageBean.setContent(page.getContent());
        pageBean.setTotal(page.getTotalElements());
        pageBean.setTotalPage(page.getTotalPages());
        return pageBean;
    }

    /**
     * 根据id获取商品
     * @param id
     * @return
     */
    public Goods findById(Long id){
        return goodsDao.find(id);
    }

    /**
     * 根据id删除商品
     * @param id
     */
    public void delete(Long id){
        goodsDao.deleteById(id);
    }

    /**
     * 查询所有商品
     * @return
     */
    public List<Goods> findAll(){
        return goodsDao.findAll();
    }


    /**
     * 查询学生发布的所有商品
     * @return
     */
    public List<Goods> findByStudent(Student student){
        return goodsDao.findByStudent(student);
    }


    /**
     * 根据商品id和学生id查询商品
     * @param id
     * @param studentid
     * @return
     */
    public Goods find(Long id,Long studentid){
        return goodsDao.find(id,studentid);
    }


    /**
     * 根据物品分类查询列表
     * @param cids
     * @param pageBean
     * @return
     */
    public PageBean<Goods> findList(List<Long> cids,PageBean<Goods> pageBean){
        List<Goods> list = goodsDao.findList(cids, pageBean.getOffSet(), pageBean.getPageSize());
        pageBean.setContent(list);
        pageBean.setTotal(goodsDao.getTotalCount(cids));
        pageBean.setTotalPage(Integer.valueOf(pageBean.getTotal()+"") / pageBean.getPageSize());
        long totalPage = pageBean.getTotal() % pageBean.getPageSize();
        if (totalPage!=0){
            pageBean.setTotalPage(pageBean.getTotalPage()+1);
        }
        return pageBean;
    }

    /**
     * 获取指定状态得物品总数
     * @param status
     * @return
     */
    public Long getTotalCount(Integer status){
        return goodsDao.getTotalCount(status);
    }

    /**
     * 获取已出售商品总数
     * @return
     */
    public Long getSoldTotalCount(){
        return getTotalCount(Goods.GOODS_STATUS_SOLD);
    }


    /**
     *
     * @param pageBean
     * @param goods
     * @return
     */
    public PageBean<Goods> findListPro(PageBean<Goods> pageBean,Goods goods){

        Specification<Goods> specification = new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + (goods.getName() == null ? "" : goods.getName()) + "%");
                predicate = criteriaBuilder.and(name);
                if(goods.getStudent()!=null&&goods.getStudent().getId()!=null){
                    Predicate student = criteriaBuilder.equal(root.get("student"), goods.getStudent().getId());
                    predicate = criteriaBuilder.and(name, student);
                }
                if(goods.getGoodsCategory()!=null&&goods.getGoodsCategory().getId()!=null){
                    Predicate goodsCategory = criteriaBuilder.equal(root.get("goodsCategory"), goods.getGoodsCategory().getId());
                    predicate = criteriaBuilder.and(predicate, goodsCategory);
                }
                return predicate;
            }
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime","recommend","flag","viewNumber");
        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<Goods> page = goodsDao.findAll(specification, pageable);
        pageBean.setContent(page.getContent());
        pageBean.setTotal(page.getTotalElements());
        pageBean.setTotalPage(page.getTotalPages());
        return pageBean;
    }


    public List<Goods> findListByName(String name){
        return goodsDao.findByName(name);
    }


}
