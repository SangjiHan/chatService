package com.app.chatservice.processors;

import java.util.logging.Logger;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.hibernate.usertype.LoggableUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Session;
import org.springframework.stereotype.Component;

import com.app.chatservice.sessionscoped.SessionId;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginProcessor {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private SessionId sessionId;

    private int maxAge = 60 * 60 * 24 * 30; // 한 달

    private Logger logger = Logger.getLogger(getClass().getName());

    private boolean stayLoggedIn;

    public void preprocessLogin(boolean stayLoggedIn, String userId){
        sessionId.setSessionId(userId);
        this.stayLoggedIn = stayLoggedIn;
        logger.info(((Boolean) stayLoggedIn).toString());
    }

    public void processLogin(Cookie loginId, Cookie keepLoggedIn, boolean b) {

        if(sessionId.isBeingLoggedOut()){ // 로그아웃 시
            logger.info("loginId: " + loginId);
            if(loginId != null){
                loginId.setValue(null);
                loginId.setMaxAge(0);
                response.addCookie(loginId);
            }
            sessionId.setBeingLoggedOut(false);
            return;
        }

        if(keepLoggedIn == null){ // 로그인 유지 쿠키가 없을 때
            keepLoggedIn = new Cookie("stayLoggedIn", ((Boolean)this.stayLoggedIn).toString());
            keepLoggedIn.setMaxAge(maxAge);
            keepLoggedIn.setHttpOnly(true);
            keepLoggedIn.setSecure(true);
            response.addCookie(keepLoggedIn);
        }
        keepLoggedIn.setValue(((Boolean)this.stayLoggedIn).toString()); 

        if(keepLoggedIn.getValue().equals("false")){ // 로그인 유지 안 한다면 아래 코드 실행 x
            logger.info("its false");
            return;
        }


        if(loginId != null){ // 쿠키에 설정된 아이디가 있을 시
            if(sessionId.getSessionId() == null){ // 세션아이디에 쿠키에있는 아이디를 설정
                sessionId.setSessionId(loginId.getValue());
            }   
            return;
        }

        if(loginId == null){ // 쿠키에 설정된 아이디가 없을 시
            if(sessionId.getSessionId() != null){ // 세션아이디가 있을 시 새 쿠키를 만듬
                Cookie cookie = new Cookie("loginId", sessionId.getSessionId());
                cookie.setMaxAge(maxAge);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                response.addCookie(cookie);
            }
        }
    }
}
