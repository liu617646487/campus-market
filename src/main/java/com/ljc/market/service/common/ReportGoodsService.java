package com.ljc.market.service.common;


import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.common.ReportGoodsDao;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.ReportGoods;
import com.ljc.market.entity.common.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * 举报物品信息Service层
 */
@Service
public class ReportGoodsService {

    @Autowired
    private ReportGoodsDao reportGoodsDao;

    /**
     * 举报商品添加/编辑 当id不为空时为编辑
     * @param reportGoods
     * @return
     */
    public ReportGoods save(ReportGoods reportGoods){
        return reportGoodsDao.save(reportGoods);
    }

    /**
     * 根据举报商品id查找商品
     * @param id
     * @return
     */
    public ReportGoods find(Long id){
        return reportGoodsDao.find(id);
    }

    /**
     * 根据id删除举报信息
     * @param id
     */
    public void delete(Long id){
        reportGoodsDao.deleteById(id);
    }


    /**
     * 查询所有举报商品
     * @return
     */
    public List<ReportGoods> findAll(){
        return reportGoodsDao.findAll();
    }


    /**
     * 根据学生查找所有举报信息
     * @return
     */
    public List<ReportGoods> findByStudent(Student student){
        return reportGoodsDao.findByStudent(student);
    }

    /**
     * 根据商品查找所有举报信息
     * @return
     */
    public List<ReportGoods> findByGoods(Goods goods){
        return reportGoodsDao.findByGoods(goods);
    }


    /**
     * 根据商品id和学生id查询查询举报商品
     * @param goodsid
     * @param studentid
     * @return
     */
    public ReportGoods find(Long goodsid,Long studentid){
        return reportGoodsDao.find(goodsid,studentid);
    }

    /**
     * 分页查询增强版
     * @param pageBean
     * @param reportGoods
     * @return
     */
    public PageBean<ReportGoods> findAllPro(PageBean<ReportGoods> pageBean,ReportGoods reportGoods,List<Goods> goodsList){
        Specification<ReportGoods> specification = new Specification<ReportGoods>(){
            @Override
            public Predicate toPredicate(Root<ReportGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if(reportGoods.getStudent()!=null&&reportGoods.getStudent().getId()!=null){
                    Predicate student = criteriaBuilder.equal(root.get("student"), reportGoods.getStudent().getId());
                    predicate = criteriaBuilder.and(student);
                }
                if (goodsList!=null&&goodsList.size()>0){
                    CriteriaBuilder.In<Object> goods = criteriaBuilder.in(root.get("goods"));
                    goods.value(goodsList);
                    if(predicate==null){
                        predicate = criteriaBuilder.and(goods);
                    }else{
                        predicate = criteriaBuilder.and(predicate, goods);
                    }
                }
                return predicate;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<ReportGoods> page = reportGoodsDao.findAll(specification, pageable);
        pageBean.setContent(page.getContent());
        pageBean.setTotal(page.getTotalElements());
        pageBean.setTotalPage(page.getTotalPages());
        return pageBean;
    }

}
