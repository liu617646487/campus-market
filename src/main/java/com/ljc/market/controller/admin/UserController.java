package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.entity.admin.User;
import com.ljc.market.service.admin.OperatorLogService;
import com.ljc.market.service.admin.RoleService;
import com.ljc.market.service.admin.UserService;
import com.ljc.market.util.SessionUtil;
import com.ljc.market.util.ValidateEntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 *
 * 后台用户管理控制器类
 */

@RequestMapping("admin/user")
@Controller
public class UserController {

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private OperatorLogService operatorLogService;

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 分页搜索用户列表
     * @param model
     * @param user
     * @param pageBean
     * @return
     */
    @GetMapping("/list")
    public String list(Model model,User user, PageBean<User> pageBean){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","用户列表");
        model.addAttribute("username",user.getUsername());
        model.addAttribute("pageBean",userService.findByName(user,pageBean));
        return "admin/user/list";
    }

    /**
     * 用户添加页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","用户添加");
        model.addAttribute("roles",roleService.findAll());
        return "admin/user/add";
    }

    /**
     * 用户添加表单处理页面
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> add(User user){
        if (user==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //判断是否有所属角色
        if(user.getRole()==null||user.getRole().getId()==null){
            return Result.error(CodeMsg.ADMIN_USER_ROLE_EMPTY );
        }
        User loginedUser = SessionUtil.getLoginedUser();
        //判断用户名是否存在
        if (userService.isExistUsername(user.getUsername(),loginedUser.getId())) {
            return Result.error(CodeMsg.ADMIN_USER_USERNAME_EXIST);
        }
        //表示验证通过，开始向数据库添加信息
        if (userService.save(user)==null){
            return Result.error(CodeMsg.ADMIN_USER_ADD_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("添加一个用户，用户名："+user.getUsername());
        return Result.success(true);
    }

    /**
     * 用户编辑页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam(name = "id",required = true) Long id){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","用户编辑");
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("user",userService.find(id));
        return "admin/user/edit";
    }

    /**
     * 用户编辑表单处理页面
     * @param user
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result<Boolean> edit(User user){
        if (user==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        if(user.getId()==null||user.getId().longValue()<=0){
            return Result.error(CodeMsg.ADMIN_USER_NO_EMPTY);
        }
        //判断是否有所属角色
        if(user.getRole()==null||user.getRole().getId()==null){
            return Result.error(CodeMsg.ADMIN_USER_ROLE_EMPTY );
        }
        //判断用户名是否存在,且是否是自己本身
        if (userService.isExistUsername(user.getUsername(),user.getId())){
            return Result.error(CodeMsg.ADMIN_USER_USERNAME_EXIST);
        }
        User existUser = userService.find(user.getId());
        if (existUser==null){
            return Result.error(CodeMsg.ADMIN_USER_ID_ERROR);
        }
        //将提交的用户信息字段user复制到数据库中已存在的findByUsername对象中
        BeanUtils.copyProperties(user,existUser,"id","createTime","updateTime");
        //表示验证通过，开始向数据库添加信息
        if (userService.save(existUser)==null){
            return Result.error(CodeMsg.ADMIN_USER_EDIT_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("编辑一个用户，用户名："+existUser.getUsername());
        return Result.success(true);
    }

    /**
     * 删除按钮
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name = "id",required = true) Long id){
        try{
            userService.deleteById(id);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_USER_DELETE_ERROR);
        }
        //数据库添加信息添加成功,向日志中添加信息
        operatorLogService.add("删除一个用户，用户ID："+id);
        return Result.success(true);
    }



}
