package com.tensquare;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class JwtParseTest {

    public static void main(String[] args) {
        JwtParser jwtParser = Jwts.parser();
        //解密先是知道对方的salt值，然后转换的时候添加密钥，最后获取JSON本体；
        Claims claims = jwtParser.setSigningKey("michael jordan").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMyIsInN1YiI6ImtvYmUgYnJ5YW50IiwiaWF0IjoxNTYwNTg5MTk4LCJleHAiOjE1NjA1ODkyMjgsImp1bXBzaG90IjoiZmFkZWF3YXkifQ.24ViXk7Syu1rJjqpg7J6oGmes6IdO2J8D6j5n6JeDpo").getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getExpiration());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getSubject());
        System.out.println(claims.get("jumpshot"));
    }
}
