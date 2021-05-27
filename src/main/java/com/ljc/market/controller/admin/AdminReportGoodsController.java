package com.ljc.market.controller.admin;

import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.GoodsCategory;
import com.ljc.market.entity.common.ReportGoods;
import com.ljc.market.entity.common.Student;
import com.ljc.market.service.common.GoodsService;
import com.ljc.market.service.common.ReportGoodsService;
import com.ljc.market.service.common.StudentService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/report_goods")
public class AdminReportGoodsController {

    @Autowired
    private ReportGoodsService reportGoodsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 举报物品列表
     * @param reportGoods
     * @param pageBean
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(ReportGoods reportGoods, PageBean<ReportGoods> pageBean, Model model){
        model.addAttribute("title","举报物品展示列表");
        if (reportGoods.getStudent()!=null&&reportGoods.getStudent().getSno()!=null){
            Student student = studentService.findBySno(reportGoods.getStudent().getSno());
            if (student!=null){
                reportGoods.setStudent(student);
            }
        }
        List<Goods> goodsList = null;
        if (reportGoods.getGoods()!=null&&reportGoods.getGoods().getName()!=null){
            goodsList = goodsService.findListByName(reportGoods.getGoods().getName());
        }
        model.addAttribute("sno",reportGoods.getStudent()==null?null:reportGoods.getStudent().getSno());
        model.addAttribute("name",reportGoods.getGoods()==null?null:reportGoods.getGoods().getName());
        model.addAttribute("pageBean",reportGoodsService.findAllPro(pageBean,reportGoods,goodsList));
        return "/admin/report_goods/list";
    }

    /**
     * 删除举报物品
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(@RequestParam("id") Long id){
        reportGoodsService.delete(id);
        return Result.success(true);
    }


}
