package com.ljc.market.util;

import com.ljc.market.constant.SessionConstant;
import com.ljc.market.entity.admin.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session统一操作工具类
 */
public class SessionUtil {

    /**
     * 获取上下文中request
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attributes==null?null:attributes.getRequest();
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        if(request!=null){
            return request.getSession();
        }
        return null;
    }

    /**
     * 根据key获取session中的value
     * @param key
     * @return
     */
    public static Object get(String key){
        HttpSession session = getSession();
        if (session!=null){
            return session.getAttribute(key);
        }
        return null;
    }

    /**
     * 设置session值
     * @param key
     * @param object
     */
    public static void set(String key,Object object){
        HttpSession session = getSession();
        if (session!=null){
            session.setAttribute(key,object);
        }
    }

    /**
     * 获取当前登录的用户
     * @return
     */
    public static User getLoginedUser(){
        HttpSession session = getSession();
        if (session!=null){
            return (User)session.getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
        }
        return null;
    }


}
