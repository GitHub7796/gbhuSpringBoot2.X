package com.gbhu.xdkt.config;

import com.gbhu.xdkt.interceptor.LoginInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InteceptorConfig implements WebMvcConfigurer {

    //登录拦截器
    LoginInteceptor loginInteceptor() {
        return new LoginInteceptor();
    }

    //注册拦截器 拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInteceptor())
                .addPathPatterns("/api/v1/pri/*/*/**")//拦截那些路径
                .excludePathPatterns("/api/v1/pri/user/register","/api/v1/pri/user/login");//不拦截那些路径
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
