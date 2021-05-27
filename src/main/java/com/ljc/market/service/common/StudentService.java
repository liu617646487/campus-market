package com.ljc.market.service.common;

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.common.StudentDao;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Student;
import com.sun.prism.shader.AlphaOne_LinearGradient_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息操作service
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * 根据学号查询学生信息
     * @param sno
     * @return
     */
    public Student findBySno(String sno){
        return studentDao.findBySno(sno);
    }


    /**
     * 学生信息保存/编辑
     * @param student
     * @return
     */
    public Student save(Student student){
        return studentDao.save(student);

    }

    /**
     * 搜索学生列表分页
     * @param pageBean
     * @param student
     * @return
     */
    public PageBean<Student> findAll(PageBean<Student> pageBean,Student student){
         ExampleMatcher matching = ExampleMatcher.matching();
         matching = matching.withMatcher("sno", ExampleMatcher.GenericPropertyMatchers.contains());
         matching = matching.withIgnorePaths("status");
         Example<Student> example = Example.of(student, matching);
         Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
         PageRequest pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
         Page<Student> page = studentDao.findAll(example, pageable);
         pageBean.setContent(page.getContent());
         pageBean.setTotal(page.getTotalElements());
         pageBean.setTotalPage(page.getTotalPages());
         return pageBean;
    }

    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    public Student findById(Long id){
        return studentDao.find(id);
    }

    /**
     * 删除学生
     * @param id
     */
    public void delete(Long id){
        studentDao.deleteById(id);
    }

}
