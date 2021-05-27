package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.GoodsCategory;
import com.ljc.market.entity.common.Student;
import com.ljc.market.service.common.GoodsCategoryService;
import com.ljc.market.service.common.GoodsService;
import com.ljc.market.service.common.StudentService;
import com.ljc.market.util.ValidateEntityUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


/**
 * 后台物品分类控制器
 */
@Controller
@RequestMapping("admin/goods")
public class AdminGoodsController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StudentService studentService;

    /**
     * 后台物品管理列表页面
     * @param model
     * @param goods
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, Goods goods, PageBean<Goods> pageBean){
        model.addAttribute("title","后台物品列表");
        if (goods.getStudent()!=null&&goods.getStudent().getSno()!=null){
            Student student = studentService.findBySno(goods.getStudent().getSno());
            if (student!=null){
                goods.setStudent(student);
            }
        }
        if (goods.getGoodsCategory()!=null&&goods.getGoodsCategory().getName()!=null){
            List<GoodsCategory> goodsCategorys = goodsCategoryService.findByName(goods.getGoodsCategory().getName());
            if (goodsCategorys!=null&&goodsCategorys.size()>0){
                goods.setGoodsCategory(goodsCategorys.get(0));
            }
        }
        model.addAttribute("name",goods.getName());
        model.addAttribute("sno",goods.getStudent()==null?null:goods.getStudent().getSno());
        model.addAttribute("goodsCategorysName",goods.getGoodsCategory()==null?null:goods.getGoodsCategory().getName());
        model.addAttribute("pageBean",goodsService.findListPro(pageBean,goods));
        return "/admin/goods/list";
    }

    /**
     * 物品上架下架提交
     * @param id
     * @param status
     * @return
     */
    @PostMapping(value = "/up_down")
    @ResponseBody
    public Result<Boolean> upDown(@RequestParam("id") Long id,@RequestParam("status") Integer status){
        Goods goods = goodsService.findById(id);
        if (goods==null){
            return Result.error(CodeMsg.ADMIN_GOODS_NO_EXIST);
        }
        if (status != Goods.GOODS_STATUS_UP&&status!=Goods.GOODS_STATUS_DOWN){
            return Result.error(CodeMsg.ADMIN_GOODS_STATUS_ERROR);
        }
        if (goods.getStatus() == Goods.GOODS_STATUS_SOLD){
            return Result.error(CodeMsg.ADMIN_GOODS_STATUS_NO_EDIT);
        }
        if (status == goods.getStatus()){
            return Result.error(CodeMsg.ADMIN_GOODS_STATUS_NO_CHANGE);
        }
        goods.setStatus(status);
        if (goodsService.save(goods)==null){
            return Result.error(CodeMsg.ADMIN_GOODS_EDIT_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 物品删除
     * @param id
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam("id") Long id){
        try{
            goodsService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_GOODS_DELETE_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 物品推荐和取消推荐
     * @param id
     * @param recommend
     * @return
     */
    @PostMapping(value = "/recommend")
    @ResponseBody
    public Result<Boolean> recommend(@RequestParam("id") Long id,@RequestParam("recommend") Integer recommend){
        Goods goods = goodsService.findById(id);
        if (goods==null){
            return Result.error(CodeMsg.ADMIN_GOODS_NO_EXIST);
        }
        if (recommend != Goods.GOODS_RECOMMEND_OFF && recommend != Goods.GOODS_RECOMMEND_ON){
            return Result.error(CodeMsg.ADMIN_GOODS_RECOMMEND_ERROR);
        }
        if (goods.getStatus() == Goods.GOODS_STATUS_SOLD){
            return Result.error(CodeMsg.ADMIN_GOODS_STATUS_NO_EDIT);
        }
        if (recommend == goods.getRecommend()){
            return Result.error(CodeMsg.ADMIN_GOODS_RECOMMEND_NO_CHANGE);
        }
        goods.setRecommend(recommend);
        if (goodsService.save(goods)==null){
            return Result.error(CodeMsg.ADMIN_GOODS_EDIT_ERROR);
        }
        return Result.success(true);
    }


}
