package com.app.chatservice.sessionscoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.app.chatservice.processors.LoginProcessor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@SessionScope
public class SessionId {
  private String sessionId;
  private boolean beingLoggedOut;
  private boolean loginAttempted;



  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public boolean isBeingLoggedOut() {
    return beingLoggedOut;
  }

  public void setBeingLoggedOut(boolean beingLoggedOut) {
    this.beingLoggedOut = beingLoggedOut;
  }

  public boolean isLoginAttempted() {
    return loginAttempted;
  }

  public void setLoginAttempted(boolean loginAttempted) {
    this.loginAttempted = loginAttempted;
  } 

}
