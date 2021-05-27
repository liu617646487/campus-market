package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.Result;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.entity.admin.Menu;
import com.ljc.market.service.admin.MenuService;
import com.ljc.market.service.admin.OperatorLogService;
import com.ljc.market.util.MenuUtil;
import com.ljc.market.util.ValidateEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台菜单管理控制器
 */
@Controller
@RequestMapping("admin/menu")
public class MenuController {

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OperatorLogService operatorLogService;

    /**
     * 菜单列表展示页面
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","菜单列表");
        List<Menu> findAll = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(findAll));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(findAll));
        model.addAttribute("thirdMenus", MenuUtil.getThirdMenus(findAll));

        return "admin/menu/list";
    }

    /**
     * 菜单添加页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","添加菜单");
        List<Menu> findAll = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(findAll));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(findAll));
        return "admin/menu/add";
    }

    /**
     * 菜单添加页面表单处理
     * @param menu
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> add(Menu menu){
        if (menu==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(menu);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }

        if (menu.getParent()!=null){
            if (menu.getParent().getId()==null){
                menu.setParent(null);
            }
        }
        //表示验证通过，开始向数据库添加信息
        if (menuService.save(menu)==null){
            Result.error(CodeMsg.ADMIN_MENU_ADD_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("添加一个菜单【"+menu+"】");
        return Result.success(true);
    }

    /**
     * 菜单编辑页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam(name = "id",required = true) Long id){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","编辑菜单");
        List<Menu> findAll = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(findAll));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(findAll));
        model.addAttribute("menu",menuService.find(id));
        return "admin/menu/edit";
    }

    /**
     * 菜单编辑页面表单处理
     * @param menu
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result<Boolean> edit(Menu menu){
        if (menu==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        if (menu.getId()==null){
            Result.error(CodeMsg.ADMIN_MENU_ID_EMPTY);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(menu);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }

        if (menu.getParent()!=null){
            if (menu.getParent().getId()==null){
                menu.setParent(null);
            }
        }
        Menu existMenu = menuService.find(menu.getId());
        if (existMenu==null){
            Result.error(CodeMsg.ADMIN_MENU_ID_ERROR);
        }
        //表示验证通过，开始向数据库添加信息
        existMenu.setName(menu.getName());
        existMenu.setUrl(menu.getUrl());
        existMenu.setIcon(menu.getIcon());
        existMenu.setSort(menu.getSort());
        existMenu.setParent(menu.getParent());
        existMenu.setIsbutton(menu.getIsbutton());
        existMenu.setIsshow(menu.getIsshow());
        if (menuService.save(existMenu)==null){
            Result.error(CodeMsg.ADMIN_MENU_EDIT_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("修改一个菜单【"+existMenu+"】");
        return Result.success(true);
    }


    /**
     * 删除菜单按钮
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name = "id",required = true) Long id){
        try{
            menuService.deleteById(id);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_MENU_DELETE_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("删除一个菜单ID【"+id+"】");
        return Result.success(true);
    }

}
