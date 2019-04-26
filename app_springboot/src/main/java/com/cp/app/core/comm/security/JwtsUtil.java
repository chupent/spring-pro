package com.cp.app.core.comm.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName JwtsUtil
 * @Description TODO Jwts生成token工具
 * @createdate 2019/4/3 星期三 14:45
 */
public class JwtsUtil {
    private static final int DEFUAl_TIME  = 30;
    public static final String SECURITY_SIGNING_KEY = "spring-security-@Jwt!&Secret^#";
    private static Date validTime(int time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, time);// 30分钟
        return calendar.getTime();
    }

    /**
     * Jwts构建token
     * @param username 用户名
     * @param time 时效
     * @return
     */
    public static String buildToken(String username,int time){
        return Jwts.builder()
                    .setSubject(username)
                    .setExpiration(validTime(time==0?DEFUAl_TIME:time))
                    .signWith(SignatureAlgorithm.HS512,SECURITY_SIGNING_KEY) //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .compact();
    }

    /**
     * 根据token获取用户名
     * @param token
     * @return
     */
    public static String getUserName(String token){
        return Jwts.parser()
                    .setSigningKey(SECURITY_SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }
}
