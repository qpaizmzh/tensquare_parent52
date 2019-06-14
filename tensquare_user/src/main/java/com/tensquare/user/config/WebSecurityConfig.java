package com.tensquare.user.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests():允许基于使用HttpServletRequest进行访问
        //csrf():添加 CSRF 支持，使用WebSecurityConfigurerAdapter时，默认启用,这是个安全策略，一般取消掉
        //anyMatchers():指定需要拦截的路径
        //permitAll()：指定这些路径允许全部权限通过
        //anyRequest():字面意思是任何的请求（指的应该是get\Post类型的请求）
        //and()应该是范围http的对象

        http
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }
}
