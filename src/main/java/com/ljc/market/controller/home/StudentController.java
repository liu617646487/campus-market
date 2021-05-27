package com.ljc.market.controller.home;

import com.alibaba.fastjson.JSONArray;
import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.Result;
import com.ljc.market.constant.SessionConstant;
import com.ljc.market.entity.common.*;
import com.ljc.market.service.common.*;
import com.ljc.market.util.SessionUtil;
import com.ljc.market.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生管理控制器
 */
@Controller
@RequestMapping("home/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private WantedGoodsService wantedGoodsService;
    @Autowired
    private ReportGoodsService reportGoodsService;
    @Autowired
    private CommentService commentService;

    /**
     * 学生登录主页
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("title","个人中心");
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        model.addAttribute("goodsList",goodsService.findByStudent(loginedStudent));
        model.addAttribute("wantedGoodsList",wantedGoodsService.findByStudent(loginedStudent));
        model.addAttribute("reportGoodsList",reportGoodsService.findByStudent(loginedStudent));
        return "/home/student/index";
    }

    /**
     * 修改个人信息提交表单
     * @param student
     * @return
     */
    @PostMapping("/edit_info")
    @ResponseBody
    public Result<Boolean> edit_info(Student student){
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        loginedStudent.setNickname(student.getNickname());
        loginedStudent.setMobile(student.getMobile());
        loginedStudent.setQq(student.getQq());
        loginedStudent.setSchool(student.getSchool());
        loginedStudent.setAcademy(student.getAcademy());
        loginedStudent.setGrade(student.getGrade());
        if (studentService.save(loginedStudent)==null){
            return Result.error(CodeMsg.HOME_STUDENT_EDIT_ERROR);
        }
        //更新用户信息
        SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,loginedStudent);
        return Result.success(true);
    }

    /**
     * 图片上传方法
     * @param headPic
     * @return
     */
    @PostMapping("/update_head_pic")
    @ResponseBody
    public Result<Boolean> updateHeadPic(@RequestParam(name = "headPic") String headPic){
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        loginedStudent.setHeadPic(headPic);
        if (studentService.save(loginedStudent)==null){
            return Result.error(CodeMsg.HOME_STUDENT_EDIT_ERROR);
        }
        //更新用户信息
        SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,loginedStudent);
        return Result.success(true);
    }

    /**
     * 跳转到商品发布页面
     * @param model
     * @return
     */
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("title","我要卖");
        List<GoodsCategory> secondCategory = goodsCategoryService.findSecondCategory();
        model.addAttribute("secondCategory", JSONArray.toJSONString(secondCategory));
        return "/home/student/publish";
    }

    /**
     * 商品发布提交表单
     * @param goods
     * @return
     */
    @PostMapping("/publish")
    @ResponseBody
    public Result<Boolean> publish(Goods goods){
        CodeMsg validate = ValidateEntityUtil.validate(goods);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        goods.setStudent(loginedStudent);
        if (goodsService.save(goods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 商品编辑页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit_goods")
    public String editGoods(@RequestParam("id") Long id, Model model){
        model.addAttribute("title","商品编辑");
        Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        Goods goods = goodsService.find(id,loginedStudent.getId());
        if (goods==null){
            model.addAttribute("msg","物品不存在！");
            return "/error/runtime_error";
        }
        model.addAttribute("goods",goods);
        return "/home/student/edit_goods";
    }

    /**
     * 编辑商品页面表单提交
     * @param goods
     * @return
     */
    @PostMapping("/edit_goods")
    @ResponseBody
    public Result<Boolean> edit_goods(Goods goods){
        CodeMsg validate = ValidateEntityUtil.validate(goods);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        Goods existGoods = goodsService.find(goods.getId(),loginedStudent.getId());
        if (existGoods==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        existGoods.setName(goods.getName());
        existGoods.setContent(goods.getContent());
        existGoods.setPhoto(goods.getPhoto());
        existGoods.setBuyPrice(goods.getBuyPrice());
        existGoods.setSellPrice(goods.getSellPrice());
        existGoods.setGoodsCategory(goods.getGoodsCategory());
        existGoods.setStudent(loginedStudent);
        if (goodsService.save(existGoods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 用户设置是否擦亮商品
     * @param id
     * @param flag
     * @return
     */
    @PostMapping(value = "/update_flag")
    @ResponseBody
    public Result<Boolean> updateFlag(@RequestParam("id") Long id,@RequestParam("flag")Integer flag){
        Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        Goods existGoods = goodsService.find(id,loginedStudent.getId());
        if (existGoods==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        existGoods.setFlag(flag);
        if (goodsService.save(existGoods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 用户设置下架商品
     * @param id
     * @param status
     * @return
     */
    @PostMapping(value = "/update_status")
    @ResponseBody
    public Result<Boolean> updateStatus(@RequestParam("id") Long id,@RequestParam("status")Integer status){
        Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        Goods existGoods = goodsService.find(id,loginedStudent.getId());
        if (existGoods==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        existGoods.setStatus(status);
        if (goodsService.save(existGoods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 发布求购物品页面
     * @param model
     * @return
     */
    @GetMapping("/publish_wanted")
    public String publishWanted(Model model){
        model.addAttribute("title","我要买");
        return "/home/student/publish_wanted";
    }

    /**
     * 发布求购物品提交表单页面
     * @param wantedGoods
     * @return
     */
    @PostMapping("/publish_wanted")
    @ResponseBody
    public Result<Boolean> publishWanted(WantedGoods wantedGoods){
        CodeMsg validate = ValidateEntityUtil.validate(wantedGoods);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        wantedGoods.setStudent(loginedStudent);
        if (wantedGoodsService.save(wantedGoods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 商品求购编辑页面跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit_wanted_goods")
    public String editWantedGoods(@RequestParam("id") Long id, Model model){
        model.addAttribute("title","求购商品编辑");
        Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        WantedGoods wantedGoods = wantedGoodsService.find(id,loginedStudent.getId());
        if (wantedGoods==null){
            model.addAttribute("msg","求购物品不存在！");
            return "/error/runtime_error";
        }
        model.addAttribute("wantedGoods",wantedGoods);
        return "/home/student/edit_wanted_goods";
    }

    /**
     * 编辑商品求购页面表单提交
     * @param wantedGoods
     * @return
     */
    @PostMapping("/edit_wanted_goods")
    @ResponseBody
    public Result<Boolean> editWantedGoods(WantedGoods wantedGoods){
        CodeMsg validate = ValidateEntityUtil.validate(wantedGoods);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        WantedGoods existWantedGoods = wantedGoodsService.find(wantedGoods.getId(),loginedStudent.getId());
        if (existWantedGoods==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        BeanUtils.copyProperties(wantedGoods,existWantedGoods,"id","createTime","updateTime","viewNumber");
        existWantedGoods.setStudent(loginedStudent);
        if (wantedGoodsService.save(existWantedGoods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 删除求购物品
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/delete_wanted_goods")
    @ResponseBody
    public Result<Boolean> deleteWantedGoods(@RequestParam("id") Long id,Model model){
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        WantedGoods wantedGoods = wantedGoodsService.find(id,loginedStudent.getId());
        if (wantedGoods==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        wantedGoodsService.delete(id);
        return Result.success(true);
    }


    /**
     * 举报物品
     * @param reportGoods
     * @return
     */
    @PostMapping("/report_goods")
    @ResponseBody
    public Result<Boolean> reportGoods(ReportGoods reportGoods){
        CodeMsg validate = ValidateEntityUtil.validate(reportGoods);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        if(reportGoods.getGoods()==null||reportGoods.getGoods().getId()==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        ReportGoods existReportGoods = reportGoodsService.find(reportGoods.getGoods().getId(), loginedStudent.getId());
        if (existReportGoods!=null){
            return Result.error(CodeMsg.HOME_STUDENT_REPORTED_GOODS);
        }
        reportGoods.setStudent(loginedStudent);
        if (reportGoodsService.save(reportGoods)==null){
            return Result.error(CodeMsg.HOME_STUDENT_REPORT_GOODS_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 删除举报物品信息
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/delete_report_goods")
    @ResponseBody
    public Result<Boolean> deleteReportGoods(@RequestParam("id") Long id,Model model){
        ReportGoods reportGoods = reportGoodsService.find(id);
        if(reportGoods==null){
            return Result.error(CodeMsg.HOME_STUDENT_REPORTED_NO_EXIST);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        if (reportGoods.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
            return Result.error(CodeMsg.HOME_STUDENT_REPORTED_NO_EXIST);
        }
        reportGoodsService.delete(id);
        return Result.success(true);
    }

    /**
     * 获取个人物品统计信息
     * @param model
     * @return
     */
    @PostMapping("/get_stats")
    @ResponseBody
    public Result<Map<String,Integer>> getStats(Model model){
        Map<String,Integer> map = new HashMap<>();
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        List<Goods> goodsList = goodsService.findByStudent(loginedStudent);
        Integer goodsTotal = goodsList.size();//已发布商品数
        Integer soldGoodsTotal = 0;//已售出商品数
        Integer downGoodsTotal = 0;//已下架商品数
        Integer upGoodsTotal = 0;//带出售商品数
        for(Goods goods:goodsList){
            if (goods.getStatus()==Goods.GOODS_STATUS_SOLD){
                soldGoodsTotal++;
            }
            if (goods.getStatus()==Goods.GOODS_STATUS_DOWN){
                downGoodsTotal++;
            }
            if (goods.getStatus()==Goods.GOODS_STATUS_UP){
                upGoodsTotal++;
            }
        }
        map.put("goodsTotal",goodsTotal);
        map.put("soldGoodsTotal",soldGoodsTotal);
        map.put("downGoodsTotal",downGoodsTotal);
        map.put("upGoodsTotal",upGoodsTotal);
        return Result.success(map);
    }

    /**
     * 评论物品
     * @param comment
     * @return
     */
    @PostMapping("/comment_goods")
    @ResponseBody
    public Result<Boolean> reportGoods(Comment comment){
        CodeMsg validate = ValidateEntityUtil.validate(comment);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        if(comment.getGoods()==null||comment.getGoods().getId()==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        Goods goods = goodsService.findById(comment.getGoods().getId());
        if (goods==null){
            return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
        }
        Student loginedStudent = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        comment.setStudent(loginedStudent);
        comment.setGoods(goods);
        if (commentService.save(comment)==null){
            return Result.error(CodeMsg.HOME_STUDENT_COMMENT_GOODS_ERROR);
        }
        return Result.success(true);
    }



}
