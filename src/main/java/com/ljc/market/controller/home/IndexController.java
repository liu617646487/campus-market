package com.ljc.market.controller.home;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.constant.SessionConstant;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Student;
import com.ljc.market.service.common.GoodsCategoryService;
import com.ljc.market.service.common.GoodsService;
import com.ljc.market.service.common.StudentService;
import com.ljc.market.util.SessionUtil;
import com.ljc.market.util.ValidateEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/index")
public class IndexController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private GoodsService goodsService;
    /**
     * 前台首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model, PageBean<Goods> pageBean,Goods goods){
        model.addAttribute("title","校园跳蚤市场");
        pageBean.setPageSize(12);
        goods.setStatus(Goods.GOODS_STATUS_UP);
        model.addAttribute("pageBean",goodsService.findList(pageBean, goods));
        model.addAttribute("name",goods.getName());
        return "home/index/index";
    }

    /**
     * 学生登录
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title","学生登录");
        return "home/index/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<Boolean> login(@RequestParam(name = "sno") String sno,@RequestParam(name = "password")String password){
        Student student = studentService.findBySno(sno);
        if (student==null){
            return Result.error(CodeMsg.HOME_STUDENT_SNO_NO_EXIST);
        }
        if (!student.getPassword().equals(password)){
            return Result.error(CodeMsg.HOME_STUDENT_PASSWORD_ERROR);
        }
        if(student.getStatus()==Student.STUDENT_STATUS_UNABLE){
            return Result.error(CodeMsg.HOME_STUDENT_UNABLE);
        }
        SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,student);
        return Result.success(true);
    }



    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,null);
        return "redirect:login";
    }

    @PostMapping("/checkSno")
    @ResponseBody
    public Result<Boolean> checkSno(@RequestParam(name = "sno") String sno){
        Student bySno = studentService.findBySno(sno);
        return Result.success(bySno==null);
    }

    /**
     * 用户注册表单提交
     * @param student
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result<Boolean> register(Student student){
        CodeMsg validate = ValidateEntityUtil.validate(student);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //基本验证通过,开始验证学号是否重复
        if (studentService.findBySno(student.getSno())!=null){
            return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SNO_EXIST);
        }
        //学号验证通过，进行数据保存
        student = studentService.save(student);
        if (student==null){
            return Result.error(CodeMsg.HOME_STUDENT_REGISTER_ERROR);
        }
        //表示注册成功，此时将学生放入session中
        SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,student);
        return Result.success(true);
    }

}
