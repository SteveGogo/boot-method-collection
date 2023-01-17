package com.learn.springsecuritymyimpl.utils;


import com.learn.springsecuritymyimpl.constant.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtTokenUtilUtil {

    private static final String JwtSecret = JwtConstant.TOKEN_SECRET;
    private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    /**
     * 创建 token
     *
     * @param userId
     * @param expireTime
     * @return
     */
    public static String createToken(String userId, String account, Date expireTime) {
        if (expireTime == null) {
            long millls = System.currentTimeMillis() + JwtConstant.TOKEN_TTL;
            expireTime = new Date(millls);
        }
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(expireTime)
                .setSubject(userId)
                .claim("uid", userId)
                .claim("account", account)
                .signWith(algorithm, JwtSecret)
                .compact();
        return token;
    }

    /**
     * 解析token  username
     */
    public static String parseTokenUserName(String token) {
        String userId = Jwts.parser()
                .setSigningKey(JwtSecret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();
        return userId;
    }

    /**
     * 解析 token 的过期时间
     *
     * @param token
     * @return
     */
    public static Date parseExpireTime(String token) {
        Date expireTime = Jwts.parser()
                .setSigningKey(JwtSecret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getExpiration();
        return expireTime;
    }

    public static String getMemberAccountByJwtToken(String jwtToken) {

        if (StringUtils.isEmpty(jwtToken)) return "";
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("account");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 根据token，判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据request判断token是否存在与有效（也就是把token取出来罢了）
     *
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("UserToken");
            if (StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
