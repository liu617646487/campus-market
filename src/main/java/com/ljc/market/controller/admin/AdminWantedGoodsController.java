package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.GoodsCategory;
import com.ljc.market.entity.common.Student;
import com.ljc.market.entity.common.WantedGoods;
import com.ljc.market.service.common.GoodsCategoryService;
import com.ljc.market.service.common.GoodsService;
import com.ljc.market.service.common.StudentService;
import com.ljc.market.service.common.WantedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 后台求购求购物品分类控制器
 */
@Controller
@RequestMapping("admin/wanted_goods")
public class AdminWantedGoodsController {

    @Autowired
    private WantedGoodsService wantedGoodsService;
    @Autowired
    private StudentService studentService;

    /**
     * 后台求购物品管理列表页面
     * @param model
     * @param wantedGoods
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, WantedGoods wantedGoods, PageBean<WantedGoods> pageBean){
        model.addAttribute("title","后台求购物品列表");
        if (wantedGoods.getStudent()!=null&&wantedGoods.getStudent().getSno()!=null){
            Student student = studentService.findBySno(wantedGoods.getStudent().getSno());
            if (student!=null){
                wantedGoods.setStudent(student);
            }
        }
        model.addAttribute("name",wantedGoods.getName());
        model.addAttribute("sno",wantedGoods.getStudent()==null?null:wantedGoods.getStudent().getSno());
        model.addAttribute("pageBean",wantedGoodsService.findListPro(pageBean,wantedGoods));
        return "/admin/wanted_goods/list";
    }



    /**
     * 求购物品删除
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam("id") Long id){
        wantedGoodsService.delete(id);
        return Result.success(true);
    }



}
