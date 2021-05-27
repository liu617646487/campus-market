package com.ljc.market.controller.home;

import com.ljc.market.bean.PageBean;
import com.ljc.market.entity.common.WantedGoods;
import com.ljc.market.service.common.GoodsService;
import com.ljc.market.service.common.WantedGoodsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 求购物品控制器
 */
@Controller
@RequestMapping("/home/wanted_goods")
public class  WantedGoodsController {

    @Autowired
    private WantedGoodsService wantedGoodsService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/list")
    public String list(Model model, PageBean<WantedGoods> pageBean,WantedGoods wantedGoods){
        model.addAttribute("title","物品求购");
        model.addAttribute("pageBean",wantedGoodsService.findList(pageBean,wantedGoods));
        return "home/wanted_goods/list";
    }


}
