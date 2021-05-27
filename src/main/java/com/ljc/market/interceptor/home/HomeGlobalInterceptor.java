package com.ljc.market.interceptor.home;

import com.alibaba.fastjson.JSON;
import com.ljc.market.bean.CodeMsg;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.constant.SessionConstant;
import com.ljc.market.entity.admin.Menu;
import com.ljc.market.entity.admin.User;
import com.ljc.market.service.common.GoodsCategoryService;
import com.ljc.market.util.MenuUtil;
import com.ljc.market.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 前台全局拦截器
 */
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private SiteConfig siteConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("zzcjxy",siteConfig.getUniversity_name());
        if (!StringUtil.isAjax(request)){
            //如果不是ajax请求，则将分类信息放入页面模板变量
            request.setAttribute("goodsCategories",goodsCategoryService.findAll());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
