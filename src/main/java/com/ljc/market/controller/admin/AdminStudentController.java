package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.Student;
import com.ljc.market.service.common.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.management.VMOptionCompositeData;


/**
 * 后台学生控制器
 */
@Controller
@RequestMapping(value = "/admin/student")
public class AdminStudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 搜索学生列表
     * @param student
     * @param pageBean
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Student student, PageBean pageBean, Model model){
        model.addAttribute("title","后台学生列表");
        model.addAttribute("sno",student.getSno());
        model.addAttribute("pageBean",studentService.findAll(pageBean,student));
        return "admin/student/list";
    }

    /**
     * 更改学生状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping(value = "/update_status")
    @ResponseBody
    public Result<Boolean> upDown(@RequestParam("id") Long id, @RequestParam("status") Integer status){
        Student student = studentService.findById(id);
        if (student==null){
            return Result.error(CodeMsg.ADMIN_STUDENT_NO_EXIST);
        }
        if (status != Student.STUDENT_STATUS_ENABLE&&status!=Student.STUDENT_STATUS_UNABLE){
            return Result.error(CodeMsg.ADMIN_GOODS_STATUS_ERROR);
        }
        if (status == student.getStatus()){
            return Result.error(CodeMsg.ADMIN_STUDENT_STATUS_NO_CHANGE);
        }
        student.setStatus(status);
        if (studentService.save(student)==null){
            return Result.error(CodeMsg.ADMIN_STUDENT_EDIT_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 学生删除
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam("id") Long id){
        try{
            studentService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_STUDENT_DELETE_ERROR);
        }
        return Result.success(true);
    }



}
