package com.tensquare;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class test {

    public static void main(String[] args) {

        //setIssuedAt用于设置签发时间
        //signWith用于设置签名秘钥
        //添加自定义属性使用Claim方法,设置多个键值对就使用setCaims方法
        JwtBuilder jwtBuilder = Jwts.builder().setId("23").setSubject("kobe bryant").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "michael jordan").setExpiration(new Date(new Date().getTime() + 30000))
                .claim("jumpshot", "fadeaway");
        System.out.println(jwtBuilder.compact());//显示加密后的密钥，每一次运行的密钥都不同，因为添加了时间的关系
    }
}
