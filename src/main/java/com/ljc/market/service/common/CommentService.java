package com.ljc.market.service.common;


import com.ljc.market.dao.common.CommentDao;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Comment;
import com.ljc.market.entity.common.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论物品信息Service层
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 评论商品添加/编辑 当id不为空时为编辑
     * @param comment
     * @return
     */
    public Comment save(Comment comment){
        return commentDao.save(comment);
    }

    /**
     * 根据评论商品id查找商品
     * @param id
     * @return
     */
    public Comment find(Long id){
        return commentDao.find(id);
    }

    /**
     * 根据id删除评论信息
     * @param id
     */
    public void delete(Long id){
        commentDao.deleteById(id);
    }


    /**
     * 查询所有评论商品
     * @return
     */
    public List<Comment> findAll(){
        return commentDao.findAll();
    }


    /**
     * 根据学生查找所有评论信息
     * @return
     */
    public List<Comment> findByStudent(Student student){
        return commentDao.findByStudent(student);
    }

    /**
     * 根据商品查找所有评论信息
     * @return
     */
    public List<Comment> findByGoods(Goods goods){
        return commentDao.findByGoods(goods);
    }


    /**
     * 根据商品id和学生id查询查询评论商品
     * @param goodsid
     * @param studentid
     * @return
     */
    public Comment find(Long goodsid,Long studentid){
        return commentDao.find(goodsid,studentid);
    }



}
