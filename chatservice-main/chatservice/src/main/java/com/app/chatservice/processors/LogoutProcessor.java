package com.app.chatservice.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;

import com.app.chatservice.sessionscoped.SessionId;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutProcessor {


    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;


    public void processLogout(SessionId sessionId){
        System.out.println("logout");
        for(Cookie cookie : request.getCookies()){
           System.out.println(cookie.getValue()); 
           cookie.setMaxAge(0);
           response.addCookie(cookie);
        }

        sessionId.setSessionId(null);
    }
}
