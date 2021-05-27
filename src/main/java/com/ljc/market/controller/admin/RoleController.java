package com.ljc.market.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.entity.admin.Menu;
import com.ljc.market.entity.admin.Role;
import com.ljc.market.service.admin.MenuService;
import com.ljc.market.service.admin.OperatorLogService;
import com.ljc.market.service.admin.RoleService;
import com.ljc.market.util.MenuUtil;
import com.ljc.market.util.ValidateEntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台角色管理控制器
 */

@Controller
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private OperatorLogService operatorLogService;

    private Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    /**
     * 分页搜索角色列表
     * @param model
     * @param role
     * @param pageBean
     * @return
     */
    @GetMapping("/list")
    public String list(Model model,Role role,PageBean<Role> pageBean){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","角色列表");
        model.addAttribute("name",role.getName());
        model.addAttribute("pageBean",roleService.findByName(role,pageBean));
        return "admin/role/list";
    }

    /**
     * 角色添加页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","角色添加");
        List<Menu> findAll = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(findAll));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(findAll));
        model.addAttribute("thirdMenus", MenuUtil.getThirdMenus(findAll));
        return "admin/role/add";
    }

    /**
     * 角色添加表单页面处理
     * @param role
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> add(Role role){
        if (role==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(role);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //表示验证通过，开始向数据库添加信息
        if (roleService.save(role)==null){
            return Result.error(CodeMsg.ADMIN_ROLE_ADD_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("添加一个角色【"+role.getName()+"】");

        return Result.success(true);
    }

    /**
     * 角色编辑页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam(name = "id",required = true) Long id){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","角色编辑");
        List<Menu> findAll = menuService.findAll();
        model.addAttribute("topMenus", MenuUtil.getTopMenus(findAll));
        model.addAttribute("secondMenus", MenuUtil.getSecondMenus(findAll));
        model.addAttribute("thirdMenus", MenuUtil.getThirdMenus(findAll));
        Role role = roleService.find(id);
        model.addAttribute("role",role);
        model.addAttribute("authorities", JSONArray.toJSON(role.getAuthorities()).toString());
        return "admin/role/edit";
    }

    /**
     * 角色编辑页面表单处理
     * @param role
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody 
    public Result<Boolean> edit(Role role){
        if (role==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        if (role.getId()==null){
            Result.error(CodeMsg.ADMIN_ROLE_ID_EMPTY);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(role);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        Role existRole = roleService.find(role.getId());
        if (existRole==null){
            return Result.error(CodeMsg.ADMIN_ROLE_ID_ERROR);
        }
        existRole.setName(role.getName());
        existRole.setAuthorities(role.getAuthorities());
        existRole.setStatus(role.getStatus());
        existRole.setRemark(role.getRemark());
        if (roleService.save(existRole)==null){
            return Result.error(CodeMsg.ADMIN_ROLE_EDIT_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("修改一个角色【"+existRole.getName()+"】");
        return Result.success(true);
    }

    /**
     * 删除角色按钮
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name = "id",required = true) Long id){
        try{
            roleService.deleteById(id);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_ROLE_DELETE_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("删除一个菜单ID【"+id+"】");
        return Result.success(true);
    }


}
