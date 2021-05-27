package com.ljc.market.interceptor.home;

import com.alibaba.fastjson.JSON;
import com.ljc.market.bean.CodeMsg;
import com.ljc.market.constant.SessionConstant;
import com.ljc.market.entity.admin.Menu;
import com.ljc.market.entity.admin.User;
import com.ljc.market.util.MenuUtil;
import com.ljc.market.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 前台登录拦截器
 */
@Component
public class HomeLoginInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(HomeLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Object object = session.getAttribute(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        if (object==null){
            //首先判断是否是ajax请求
            if (StringUtil.isAjax(request)){
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
                return false;
            }
            log.info("学生未登录或session已失效,重定向到登录页面,当前URI==>"+requestURI);
            //说明是普通请求可直接重定向到登录页面
            //用户未登录或session已失效,重定向到登录页面
            response.sendRedirect("/home/index/login");
            return false;
        }
        log.info("该请求符合登录要求，前台登录拦截器放行，放行地址为==>"+requestURI);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
