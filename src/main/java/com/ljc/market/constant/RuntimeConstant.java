package com.ljc.market.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 */
public class RuntimeConstant {

    //后台登录拦截器无需拦截的url
    public static List<String> loginExcludePathPatterns = Arrays.asList(
            "/admin/system/login",
            "/admin/css/**",
            "/admin/fonts/**",
            "/admin/images/**",
            "/admin/js/**",
            "/error",
            "/captcha/**",
            "/photo/view",
            "/home/**"
    );

    //后台权限拦截器无需拦截的url
    public static List<String> authorityExcludePathPatterns = Arrays.asList(
            "/admin/system/login",
            "/admin/system/logout",
            "/admin/system/index",
            "/admin/system/update_userinfo",
            "/admin/system/update_pwd",
            "/admin/system/no_right",
            "/admin/css/**",
            "/admin/fonts/**",
            "/admin/images/**",
            "/admin/js/**",
            "/error",
            "/captcha/**",
            "/photo/view",
            "/home/**"
            );

    //前台全局拦截器无需拦截的url
    public static List<String> homeGlobalExcludePathPatterns = Arrays.asList(
            "/admin/**",
            "/error",
            "/captcha/**",
            "/photo/view",
            "/home/css/**",
            "/home/imgs/**",
            "/home/js/**",
            "/upload/**"
    );

    //前台登录拦截器无需拦截的url
    public static List<String> homeLoginExcludePathPatterns = Arrays.asList(
            "/admin/**",
            "/error",
            "/captcha/**",
            "/photo/view",
            "/home/css/**",
            "/home/imgs/**",
            "/home/js/**",
            "/home/index/**",
            "/home/goods/**",
            "/home/wanted_goods/**",
            "/upload/**"
            );


}
