package com.tensquare.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;


@Component
public class WebFilter extends ZuulFilter {


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("后台启动服务器");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //转发options的请求过来？？不是get？为什么？？
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }

        String url = request.getRequestURL().toString();

        if (url.indexOf("/admin/login") > 0) {
            System.out.println("登陆页面" + url);
            return null;
        }

        //获取相对应的头信息
        String authHeader = request.getHeader("Authorization");

        if (!StringUtils.isBlank(authHeader) && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);

            if (claims != null) {
                String role = claims.get("role").toString();
                if ("admin".equals(role)) {
                    //如果是admin权限的话，直接转发自己的请求到实际需要的微服务当中
                    currentContext.addZuulRequestHeader("Authrozation", authHeader);
                    System.out.println("验证通过，添加了头信息： " + authHeader);
                    return null;
                }
            }
        }
        //权限不通过的时候，直接终止响应，返回错误代码
        currentContext.setSendZuulResponse(false);//终止运行，不符合权限规则,直接绕过微服务的转发，并且设置返回的错误码
        currentContext.setResponseStatusCode(401);//http状态码
        currentContext.setResponseBody("无权访问");

        currentContext.getResponse().setContentType("text/html;charset=UTF-8");

        return null;

    }
}
