package com.ljc.market.interceptor.admin;

import com.alibaba.fastjson.JSON;
import com.ljc.market.bean.CodeMsg;
import com.ljc.market.entity.admin.Menu;
import com.ljc.market.entity.admin.User;
import com.ljc.market.util.MenuUtil;
import com.ljc.market.util.SessionUtil;
import com.ljc.market.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 权限拦截器
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取当前拦截的uri（菜单名称）
        String requestURI = request.getRequestURI();
        User loginedUser = SessionUtil.getLoginedUser();
        log.info("进入权限拦截器,当前URI==>"+requestURI);
        //获取用户拥有的菜单
        List<Menu> authorities = loginedUser.getRole().getAuthorities();
        //判断当前访问的菜单该用户是否有权限
        if(!MenuUtil.isExistUrl(requestURI,authorities)){
            //首先判断是否是ajax请求
            if (StringUtil.isAjax(request)){
                log.info("ajax请求，用户无权限,重定向到无权限页面,当前URI==>"+requestURI);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(CodeMsg.ADMIN_NO_RIGHT));
                return false;
            }
            log.info("普通请求，用户无权限,重定向到无权限页面,当前URI==>"+requestURI);
            //说明是普通请求可直接重定向到无权限页面
            response.sendRedirect("/admin/system/no_right");
            return false;
        }
        log.info("该请求符合权限要求，权限拦截器放行，放行地址为==>"+requestURI);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
