package com.ljc.market.service.admin;

/**
 * 后台操作日志类数据库操作Service
 */

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.admin.OperatorLogDao;
import com.ljc.market.entity.admin.OperatorLog;
import com.ljc.market.entity.admin.Role;
import com.ljc.market.entity.admin.User;
import com.ljc.market.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OperatorLogService {

    @Autowired
    private OperatorLogDao operatorLogDao;

    /**
     * 添加修改操作日志，当id不为空时修改，id为空时自动增加一条记录
     * @param operaterLog
     * @return
     */
    public OperatorLog save(OperatorLog operaterLog){
        return operatorLogDao.save(operaterLog);
    }

    /**
     * 获取指定条数的操作日志列表
     * @param size
     * @return
     */
    public List<OperatorLog> findLastestLog(int size){
        return operatorLogDao.findLastestLog(size);
    }


    public void add(String operator,String content){
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setOperator(operator);
        operatorLog.setContent(content);
        operatorLogDao.save(operatorLog);
    }

    public void add(String content){
        User loginedUser = SessionUtil.getLoginedUser();
        add(loginedUser==null?"未知（获取登录用户失败）":loginedUser.getUsername(),content);
    }

    public OperatorLog findById(Long id){
        return operatorLogDao.find(id);
    }

    public List<OperatorLog> findAll(){
        return operatorLogDao.findAll();
    }

    public void deleteById(Long id){
        operatorLogDao.deleteById(id);
    }
    public void deleteAll(){
        operatorLogDao.deleteAll();
    }

    public PageBean<OperatorLog> findList(OperatorLog operatorLog, PageBean<OperatorLog> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("operator", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<OperatorLog> example = Example.of(operatorLog, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<OperatorLog> findAll = operatorLogDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 操作日志总数
     * @return
     */
    public Long total(){
        return operatorLogDao.count();
    }

}
