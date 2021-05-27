package com.ljc.market.config.admin;
/**
 * 用来配置拦截器的配置文件
 */

import com.ljc.market.interceptor.admin.AuthorityInterceptor;
import com.ljc.market.interceptor.admin.AdminLoginInterceptor;
import com.ljc.market.constant.RuntimeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Autowired
    private AuthorityInterceptor authorityInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.loginExcludePathPatterns);
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.authorityExcludePathPatterns);
    }
}
