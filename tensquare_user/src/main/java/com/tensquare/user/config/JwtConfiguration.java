package com.tensquare.user.config;

import com.tensquare.user.inteceptor.JwtInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class JwtConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInteceptor jwtInteceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInteceptor).addPathPatterns("/**").excludePathPatterns("**/login/**");
    }


}
