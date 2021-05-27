package com.ljc.market.listener;

import com.ljc.market.service.admin.OperatorLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器用来控制session的监听和销毁
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    @Autowired
    private OperatorLogService operatorLogService;

    Logger log = LoggerFactory.getLogger(HttpSessionListener.class);

    public static long onlineUserCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("进入session创建事件，当前在线用户数："+(++onlineUserCount));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("进入session销毁事件当前在线用户数："+(--onlineUserCount));
    }
}
