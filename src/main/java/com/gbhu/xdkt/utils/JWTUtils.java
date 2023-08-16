package com.gbhu.xdkt.utils;

import com.gbhu.xdkt.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {
    //过期时间，一周 单位ms
    private static final long EXPIRE = 60000 * 60 * 24 * 7;


    //密钥
    private static final String SECRET = "gb.hu.xdtk";


    //令牌前缀
    private static final String TOKEN_PREFIX = "xdtk";

    //主题
    private static final String SUBJECT = "xdtk";



    public static String getToken(User user) {
        String token = Jwts.builder()//创建JWT对象

                //Payload 部分
                .setSubject(SUBJECT)//设置主题
                //以下3行设置私有的Payload，方便后续从中拿，避免读库
                .claim("head_imag", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())//颁发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//过期时间

                //Signature 部分
                .signWith(SignatureAlgorithm.HS256, SECRET)//加密算法

                .compact();//生成token（1.编码 Header 和 Payload 2.生成签名 3.拼接字符串）
        token += TOKEN_PREFIX;
        return token;
    }

    /**
     * 返回Claims,Claims中有很多我们加密时候存放的各种信息
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            //防止解密报异常
            final Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)//说明加密密钥
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            //ok,we can trust the token
            return claims;
        } catch (Exception e) {
            //不是我颁发的或者以及过期都会报错
            //dont trust the token！
            e.printStackTrace();
            return null;
        }
    }

}
