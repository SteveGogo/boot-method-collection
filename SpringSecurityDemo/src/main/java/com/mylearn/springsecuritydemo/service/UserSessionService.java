package com.mylearn.springsecuritydemo.service;

import com.mylearn.springsecuritydemo.auth.CustomerUserDetails;

public interface UserSessionService {
     void saveSession(CustomerUserDetails userDetails, int second);

     CustomerUserDetails getSessionByUsername(String username);

     void destroySession(String username);

    void saveTokenTimestamp(String username, long mills);


    Long getTokenTimestamp(String username);

}
