package com.tensquare_web.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";//过滤器的类型有：pre(在请求之前被调用)\route在路由请求的时候被调用\post在route和error过滤器之后被调用\error请求发生错误的时候被调用
    }

    @Override
    public int filterOrder() {
        return 0;//调用的优先级，数字越小，优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true; //这个方法用于是否启用这个拦截器，启用的就是true,不启用就是false
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("经过了拦截器");
        RequestContext requestContext = RequestContext.getCurrentContext();//Zuul里面的使用类
        //获取请求，进而获取请求header中的秘钥：
        HttpServletRequest request = requestContext.getRequest();
        //获取请求的秘钥：
        String authorization = request.getHeader("Authorization");

        //从http请求发送过来之后， 把这个秘钥交由zuul,由zuul再指向特定的微服务，将请求头发送过去
        if (StringUtils.isBlank(authorization)) {
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
