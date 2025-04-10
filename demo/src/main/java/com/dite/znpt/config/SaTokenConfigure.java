//package com.dite.znpt.configuration;
//
//import cn.dev33.satoken.config.SaTokenConfig;
//import cn.dev33.satoken.context.SaHolder;
//import cn.dev33.satoken.exception.NotLoginException;
//import cn.dev33.satoken.filter.SaServletFilter;
//import cn.dev33.satoken.interceptor.SaInterceptor;
//import cn.dev33.satoken.stp.StpUtil;
//import cn.dev33.satoken.util.SaResult;
//import cn.hutool.extra.spring.SpringUtil;
//import com.gaea.common.common.constants.Constants;
//import com.gaea.common.common.enums.CommonEOS;
//import com.gaea.data.base.context.UserContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @description: Sa-Token 权限认证 配置类
// */
//@Slf4j
//@Configuration
//public class SaTokenConfigure implements WebMvcConfigurer {
//
//    @Value("${spring.profiles.active}")
//    private String profile;
//
//    /**
//     * @author wujinsong
//     * @date 2021/11/20 9:22 下午
//     * @description: 注册 Sa-Token 全局过滤器
//     * @Param []
//     * @Return cn.dev33.satoken.filter.SaServletFilter
//     */
//    @Bean
//    public SaServletFilter getSaServletFilter() {
//
//        return new SaServletFilter().addInclude("/**").addExclude("/favicon.ico", "/user/login").setAuth(obj -> {
//            // 校验 Id-Token 身份凭证
//            if (!Constants.PROFILE_DEV.equals(profile)) {
////                StpUtil.checkLogin();
//                boolean isLogin = SpringUtil.getBean(UserContext.class).checkLogin();
//                if (!isLogin) {
//                    throw NotLoginException.newInstance(StpUtil.TYPE, NotLoginException.NOT_TOKEN);
//                }
//            }
//        }).setError(e -> {
//            SaHolder.getResponse().setHeader("Content-Type", "application/json; charset=utf-8");
//            return SaResult.error(CommonEOS.E25000007.getName());
//        });
//    }
//
//    @Bean
//    @Primary
//    public SaTokenConfig getSaTokenConfigPrimary() {
//        SaTokenConfig config = new SaTokenConfig();
//        config.setTokenName("satoken");
//        config.setActivityTimeout(6000 * 30);
//        config.setIsConcurrent(true);
//        config.setIsShare(true);
//        config.setTokenStyle("uuid");
//        config.setIsLog(false);
//        return config;
//    }
//
//    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
//        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**").excludePathPatterns("/favicon.ico", "/user/login");
//    }
//}
