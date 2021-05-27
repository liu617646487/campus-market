package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.entity.common.GoodsCategory;
import com.ljc.market.service.common.GoodsCategoryService;
import com.ljc.market.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/goods_category")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 物品分类管理列表页面
     * @param model
     * @param goodsCategory
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, GoodsCategory goodsCategory, PageBean pageBean){
        model.addAttribute("title","物品分类列表");
        model.addAttribute("name",goodsCategory.getName());
        model.addAttribute("pageBean",goodsCategoryService.findList(pageBean,goodsCategory));
        return "/admin/goods_category/list";
    }

    /**
     * 物品分类添加页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("title","添加物品分类");
        model.addAttribute("goodsCategories",goodsCategoryService.findTopCategory());
        return "/admin/goods_category/add";
    }

    /**
     * 物品分类表单提交
     * @param goodsCategory
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> add(GoodsCategory goodsCategory){
        if(goodsCategory==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        if (goodsCategory.getParent()!=null&&goodsCategory.getParent().getId()==null){
            goodsCategory.setParent(null);
        }
        if (goodsCategoryService.save(goodsCategory)==null){
            return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_ADD_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 物品分类编辑页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit")
    public String edit(Model model,@RequestParam(name="id",required = true)Long id){
        model.addAttribute("title","编辑物品分类");
        model.addAttribute("goodsCategory",goodsCategoryService.findById(id));
        model.addAttribute("goodsCategories",goodsCategoryService.findTopCategory());
        return "/admin/goods_category/edit";
    }


    /**
     * 物品分类表单提交
     * @param goodsCategory
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result<Boolean> edit(GoodsCategory goodsCategory){
        if(goodsCategory==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
        if (validate.getCode()!=CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        if (goodsCategory.getParent()!=null&&goodsCategory.getParent().getId()==null){
            goodsCategory.setParent(null);
        }
        if (goodsCategory.getId()==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        GoodsCategory existGoodsCategory = goodsCategoryService.findById(goodsCategory.getId());
        if (existGoodsCategory==null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        BeanUtils.copyProperties(goodsCategory,existGoodsCategory,"id","createTime","updateTime");
        if (goodsCategoryService.save(existGoodsCategory)==null){
            return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_EDIT_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 物品分类删除操作
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name = "id") Long id){
        try{
            goodsCategoryService.delete(id);
        }catch(Exception e){
            return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_DELETE_ERROR);
        }
        return Result.success(true);
    }


}
