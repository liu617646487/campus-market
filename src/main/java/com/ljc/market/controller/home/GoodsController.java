package com.ljc.market.controller.home;

import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.entity.common.Comment;
import com.ljc.market.entity.common.Goods;
import com.ljc.market.entity.common.GoodsCategory;
import com.ljc.market.service.common.CommentService;
import com.ljc.market.service.common.GoodsCategoryService;
import com.ljc.market.service.common.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/home/goods")
@Controller
public class GoodsController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CommentService commentService;

    /**
     * 物品详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(@RequestParam("id") Long id, Model model){
        model.addAttribute("title","物品详情");
        Goods goods = goodsService.findById(id);
        List<Comment> commentList = commentService.findByGoods(goods);
        if (goods==null){
            model.addAttribute("msg","物品不存在！");
            return "/error/runtime_error";
        }
        //更新浏览量
        goods.setViewNumber(goods.getViewNumber()+1);
        goodsService.save(goods);
        model.addAttribute("goods",goods);
        model.addAttribute("commentList",commentList);
        return "/home/goods/detail";
    }

    /**
     * 根据商品分类查询商品
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(@RequestParam("cid") Long cid, Model model, PageBean<Goods> pageBean){
        model.addAttribute("title","校园跳蚤市场");
        pageBean.setPageSize(12);
        GoodsCategory goodsCategory = goodsCategoryService.findById(cid);
        if (goodsCategory == null) {
            model.addAttribute("msg","物品分类不存在！");
            return "/error/runtime_error";
        }
        //接下来分两步，判断是大分类还是小分类
        List<Long> cids = new ArrayList<Long>();
        cids.add(cid);
        if(goodsCategory.getParent()==null){
            //选中顶级分类，然后获取顶级分裂下的子类
            List<GoodsCategory> childrenGoodsCategory = goodsCategoryService.findChildren(goodsCategory);
            for(GoodsCategory gc:childrenGoodsCategory){
                cids.add(gc.getId());
            }
        }
        PageBean<Goods> goodsList = goodsService.findList(cids, pageBean);
        model.addAttribute("pageBean",goodsList);
        model.addAttribute("gc",goodsCategory);
        return "/home/goods/list";
    }

    /**
     * 获取已出售得商品总数
     * @return
     */
    @PostMapping("get_sold_total")
    @ResponseBody
    public Result<Long> getSoldTotal(){
        return Result.success(goodsService.getSoldTotalCount());
    }

}
