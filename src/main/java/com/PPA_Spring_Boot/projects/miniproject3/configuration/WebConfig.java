package com.PPA_Spring_Boot.projects.miniproject3.configuration;

import com.PPA_Spring_Boot.projects.miniproject3.interceptors.BasicAuthInterceptor;
import com.PPA_Spring_Boot.projects.miniproject3.interceptors.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private BasicAuthInterceptor basicAuthInterceptor;

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(basicAuthInterceptor).addPathPatterns("/api/*");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/*");
    }
}
