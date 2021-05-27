package com.ljc.market.service.admin;

import com.ljc.market.dao.admin.MenuDao;
import com.ljc.market.entity.admin.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * 后台菜单数据库操作层
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 新增/修改菜单
     * @param menu
     * @return
     */
    public Menu save(Menu menu){
        return menuDao.save(menu);
    }

    /**
     * 获取所有菜单列表
     * @return
     */
    public List<Menu> findAll(){
        return menuDao.findAll();
    }

    /**
     * 根据id查找菜单
     * @param id
     * @return
     */
    public Menu find(Long id){
        return menuDao.find(id);
    }

    /**
     * 根据id删除菜单
     * @param id
     */
    public void deleteById(Long id){
        menuDao.deleteById(id);
    }

}
