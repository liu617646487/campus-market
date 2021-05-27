package com.ljc.market.util;

import com.ljc.market.entity.admin.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单工具类
 */
public class MenuUtil {

    /**
     * 获取一级菜单分类
     * @param menus
     * @return
     */
    public static List<Menu> getTopMenus(List<Menu> menus){
        List<Menu> topMenus = new ArrayList<Menu>();
        for (Menu menu:menus){
            if (menu.getParent()==null){
                topMenus.add(menu);
            }
        }
        return topMenus;
    }

    /**
     * 获取二级菜单分类
     * @param menus
     * @return
     */
    public static List<Menu> getSecondMenus(List<Menu> menus){
        List<Menu> secondMenus = new ArrayList<Menu>();
        for (Menu menu:menus){
            if (menu.getParent()!=null&&menu.getParent().getParent()==null){
                secondMenus.add(menu);
            }
        }
        return secondMenus;
    }

    /**
     * 获取三级菜单分类
     * @param menus
     * @return
     */
    public static List<Menu> getThirdMenus(List<Menu> menus){
        List<Menu> thirdMenus = new ArrayList<Menu>();
        for (Menu menu:menus){
            if (menu.getParent()!=null&&menu.getParent().getParent()!=null){
                thirdMenus.add(menu);
            }
        }
        return thirdMenus;
    }

    /**
     * 根据菜单url获取菜单id
     * @param url
     * @param menus
     * @return
     */
    public static Long getMenuIdByUrl(String url,List<Menu> menus){
        for (Menu menu:menus){
            if (url.equals(menu.getUrl())){
                return menu.getId();
            }
        }
        return null;
    }

    /**
     * 根据父id获取所有子菜单列表
     * @param parentId
     * @param menus
     * @return
     */
    public static List<Menu> getChildren(Long parentId,List<Menu> menus){
        List<Menu> ChildrenMenus = new ArrayList<Menu>();
        if (parentId!=null){
            for (Menu menu:menus){
                if (menu.getParent()!=null&&menu.getParent().getId().longValue()==parentId.longValue()){
                    ChildrenMenus.add(menu);
                }
            }
        }
        return ChildrenMenus;
    }

    /**
     * 判断给定的url是否存在指定的列表中
     */
    public static boolean isExistUrl(String url,List<Menu> menus){
        if (url!=null){
            for (Menu menu:menus){
                if (menu.getUrl()!=null){
                    if (menu.getUrl().contains(url)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
