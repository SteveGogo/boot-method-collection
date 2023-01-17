package com.learn.springsecuritymyimpl.constant;

public class JwtConstant {
    //    //私钥
    public static final String TOKEN_SECRET = "tokensecret";

    //token 过期,默认 2 小时
    public static final long TOKEN_TTL = 2 * 60 * 60 * 1000;
}
