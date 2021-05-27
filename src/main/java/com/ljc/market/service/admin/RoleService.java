package com.ljc.market.service.admin;

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.admin.RoleDao;
import com.ljc.market.entity.admin.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台角色数据库操作层
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 新增/修改角色
     * @param role
     * @return
     */
    public Role save(Role role){
        return roleDao.save(role);
    }

    /**
     * 获取所有角色列表
     * @return
     */
    public List<Role> findAll(){
        return roleDao.findAll();
    }

    /**
     * 分页按角色名称搜索角色列表
     * @param role
     * @param pageBean
     * @return
     */
    public PageBean<Role> findByName(Role role,PageBean<Role> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        withMatcher = withMatcher.withIgnorePaths("status");
        Example<Role> example = Example.of(role, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Role> findAll = roleDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 根据id查找角色
     * @param id
     * @return
     */
    public Role find(Long id){
        return roleDao.find(id);
    }

    /**
     * 根据id删除角色
     * @param id
     */
    public void deleteById(Long id){
        roleDao.deleteById(id);
    }

}
