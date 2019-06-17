package com.tensquare.qa.inteceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInteceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil util;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");

        //添加角色权限信息到请求头，只有请求头包含了管理员的信息才能进行删除，否则是不能删除的
        //也可以直接放到请求体中，但是说不安全？？
        String auth = request.getHeader("Authorization");
        if (!StringUtils.isBlank(auth) && auth.startsWith("Bearer")) {

            String token = auth.substring(7);

            try {
                Claims claims = util.parseJWT(token);

                if (claims != null) {
                    if ("admin".equals(claims.get("roles"))) {
                        //为什么不是直接把claims放到请求的属性上？
                        request.setAttribute("admin_claims", claims);
                    }
                    if ("user".equals(claims.get("roles"))) {
                        request.setAttribute("user_claims", claims);
                    }
                }


            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException("令牌不对");
            }
        }


        return true;
    }
}
