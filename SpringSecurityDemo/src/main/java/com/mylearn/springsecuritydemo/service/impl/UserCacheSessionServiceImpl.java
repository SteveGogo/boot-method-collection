//package com.mylearn.springsecuritydemo.service;
//
//
//import com.mylearn.springsecuritydemo.auth.CustomerUserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//
//@Service
//public class UserCacheSessionServiceImpl implements UserSessionService  {
//
//    @Resource
//    private RedisManager redisManager;
//
//    private static final String USER_SESSION_PREFIX = "USER-SESSION:";
//    private static final String USER_TOKEN_TIMESTAMP_PREFIX = "USER-TOKEN-TIMESTAMP:";
//
//
//    @Override
//    public void saveSession(CustomerUserDetails userDetails, int second) {
//        String username = userDetails.getUsername();
//        String key = USER_SESSION_PREFIX + username;
//        redisManager.setexToJson(key,second,userDetails);
//    }
//
//    @Override
//    public CustomerUserDetails getSessionByUsername(String username) {
//        String key = USER_SESSION_PREFIX + username;
//        return redisManager.getFromJson(key,CustomerUserDetails.class);
//    }
//
//    @Override
//    public void destroySession(String username) {
//        String key = USER_SESSION_PREFIX + username;
//        String key1 = USER_TOKEN_TIMESTAMP_PREFIX + username;
//        redisManager.del(key,key1);
//    }
//
//
//    @Override
//    public void saveTokenTimestamp(String username, long mills) {
//        String key = USER_TOKEN_TIMESTAMP_PREFIX + username;
//        redisManager.set(key, String.valueOf(mills));
//    }
//
//    @Override
//    public Long getTokenTimestamp(String username) {
//        String key = USER_TOKEN_TIMESTAMP_PREFIX + username;
//        return Long.valueOf(redisManager.get(key));
//    }
//}
//
