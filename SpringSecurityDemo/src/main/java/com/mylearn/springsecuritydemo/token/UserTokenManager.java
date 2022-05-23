package com.mylearn.springsecuritydemo.token;

import com.mylearn.springsecuritydemo.auth.CustomerUserDetails;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * UserTokenManager
 * token管理
 */
@Component
public class UserTokenManager {

    @Autowired
    private UserAuthProperties userAuthProperties;
    @Autowired
    private UserSessionService userSessionService;

    /**
     * 颁发token
     * @param principal
     * @author zgd
     * @date 2019/7/19 15:34
     * @return void
     */
    public void awardAccessToken(CustomerUserDetails principal, boolean isRefresh) {
        //颁发token 确定时间戳，保存在session中和token中
        long mill = System.currentTimeMillis();
        userSessionService.saveSession(principal);
        userSessionService.saveTokenTimestamp(principal.getUsername(),mill);

        Map<String,Object> param = new HashMap<>(4);
        param.put(UserConstants.USER_ID,principal.getId());
        param.put(SecurityConstants.TIME_STAMP,mill);

        String token = JwtTokenUtil.generateToken(principal.getUsername(), param,userAuthProperties.getJwtExpirationTime());
        HashMap<String, String> map = Maps.newHashMapWithExpectedSize(1);
        map.put(SecurityConstants.HEADER,token);
        int code = isRefresh ? 201 : 200;
        ResponseUtil.outWithHeader(code,ResultUtil.success(),map);
    }
}
