package com.ljc.market.controller.admin;


import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.Result;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.constant.SessionConstant;
import com.ljc.market.entity.admin.DatabaseBak;
import com.ljc.market.entity.admin.OperatorLog;
import com.ljc.market.entity.admin.Role;
import com.ljc.market.entity.admin.User;
import com.ljc.market.listener.SessionListener;
import com.ljc.market.service.admin.DatabaseBakService;
import com.ljc.market.service.admin.OperatorLogService;
import com.ljc.market.service.admin.UserService;
import com.ljc.market.util.SessionUtil;
import com.ljc.market.util.StringUtil;
import com.ljc.market.util.ValidateEntityUtil;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 系统控制器
 */

@Controller
@RequestMapping("/admin/system")
public class SystemController {

    private Logger log = LoggerFactory.getLogger(SystemController.class);
    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private OperatorLogService operatorLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private DatabaseBakService databaseBakService;

    //登录页面
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        return "admin/system/login";
    }

    //Ajax请求
    @PostMapping("/login")
    @ResponseBody
    public Result<Boolean> login(HttpServletRequest request,User user, String captcha){
        if (user==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //表示实体信息合法，开始验证验证码是否为空
        if (StringUtils.isEmpty(captcha)){
            return Result.error(CodeMsg.CAPTCHA_EMPTY);
        }
        //说明验证码不为空，从session中取验证码
        Object admin_login = request.getSession().getAttribute("admin_login");
        if (admin_login==null){
            return Result.error(CodeMsg.SESSION_EXPIRED);
        }
        //表示session未失效，进一步判断用户填写的验证码是否正确
        /*if (!captcha.equalsIgnoreCase(admin_login.toString())){
            return Result.error(CodeMsg.CAPTCHA_ERROR);
        }*/
        //表示验证码正确，开始校验数据用户名和密码，查询数据库
        User byUsername = userService.findByUsername(user.getUsername());
        //判断数据库中是否有此账户
        if (byUsername==null){
            return Result.error(CodeMsg.ADMIN_USERNAME_NO_EXIST);
        }
        //表示用户存在，判断密码是否正确
        if (!byUsername.getPassword().equals(user.getPassword())){
            return Result.error(CodeMsg.ADMIN_PASSWORD_ERROR);
        }
        //表示用户正确，判断用户是否可用
        if (byUsername.getStatus()==User.ADMIN_USER_STATUS_UNABLE){
            return Result.error(CodeMsg.ADMIN_USER_UNABLE);
        }
        //表示用户可用，判断用户所属角色是否可用
        if (byUsername.getRole()==null||byUsername.getRole().getStatus()== Role.ADMIN_ROLE_STATUS_UNABLE){
            return Result.error(CodeMsg.ADMIN_USER_ROLE_UNABLE);
        }
        //表示用户所属角色可用，判断用户是否拥有权限
        if (byUsername.getRole().getAuthorities()==null||byUsername.getRole().getAuthorities().size()==0){
            return Result.error(CodeMsg.ADMIN_USER_ROLE_AUTHORITIES_EMPTY);
        }
        //表示用户正常，密码正确，用户可用，将user放入session
        request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY,byUsername);
        //销毁验证码
        request.getSession().setAttribute("admin_login",null);
        //记录日志
        operatorLogService.add("用户【"+user.getUsername()+"】于【"+ StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"】登录系统！");
        log.info("用户登录成功，user = "+byUsername);
        return Result.success(true);
    }


    //登录成功后的系统主页
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("userTotal",userService.total());
        model.addAttribute("onlineUserTotal", SessionListener.onlineUserCount);
        model.addAttribute("operatorTotal",operatorLogService.total());
        model.addAttribute("databaseBakTotal",databaseBakService.total());
        List<OperatorLog> lastestLogs = operatorLogService.findLastestLog(10);
        model.addAttribute("lastestLogs",lastestLogs);
        return "admin/system/index";
    }

    //退出系统
    @GetMapping("/logout")
    public String logout(){
        User loginedUser = SessionUtil.getLoginedUser();
        if (loginedUser!=null){
            SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY,null);
        }
        return "redircect:login";
    }

    //系统无权限提示页面
    @GetMapping("/no_right")
    public String noRight(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        return "admin/system/no_right";
    }

    @GetMapping("/update_userinfo")
    public String updateUserInfo(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        return "admin/system/update_userinfo";
    }

    @PostMapping("/update_userinfo")
    @ResponseBody
    public Result<Boolean> updateUserInfo(User user){
        User loginedUser = SessionUtil.getLoginedUser();
        loginedUser.setHeadPic(user.getHeadPic());
        loginedUser.setMobile(user.getMobile());
        loginedUser.setEmail(user.getEmail());
        //保存数据
        if (userService.save(loginedUser)==null){
            return Result.error(CodeMsg.ADMIN_USER_EDIT_ERROR);
        }
        //更新session中的用户
        SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY,loginedUser);
        return Result.success(true);
    }

    @GetMapping("/update_pwd")
    public String updatePwd(Model model){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        return "admin/system/update_pwd";
    }

    @PostMapping("/update_pwd")
    @ResponseBody
    public Result<Boolean> updatePwd(@RequestParam(name = "oldpwd",required = true) String oldpwd,
                                     @RequestParam(name = "newpwd",required = true) String newpwd,
                                     @RequestParam(name = "confirmpwd",required = true) String confirmpwd){
        User loginedUser = SessionUtil.getLoginedUser();
        if (!loginedUser.getPassword().equals(oldpwd)){
            return Result.error(CodeMsg.ADMIN_OLDPWD_ERROR);
        }
        if (!newpwd.equals(confirmpwd)){
            return Result.error(CodeMsg.ADMIN_CONFIRMPWD_ERROR);
        }
        loginedUser.setPassword(newpwd);
        if (userService.save(loginedUser)==null){
            return Result.error(CodeMsg.ADMIN_USER_EDIT_ERROR);
        }
        //更新session中的用户
        SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY,loginedUser);
        return Result.success(true);
    }


}
