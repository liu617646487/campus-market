package com.ljc.market.config.home;
/**
 * 用来配置拦截器的配置文件
 */

import com.ljc.market.constant.RuntimeConstant;
import com.ljc.market.interceptor.home.HomeGlobalInterceptor;
import com.ljc.market.interceptor.home.HomeLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class HomeWebConfig implements WebMvcConfigurer {

    @Autowired
    private HomeLoginInterceptor homeLoginInterceptor;

    @Autowired
    private HomeGlobalInterceptor homeGlobalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(homeGlobalInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.homeGlobalExcludePathPatterns);
        registry.addInterceptor(homeLoginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.homeLoginExcludePathPatterns);

    }
}
