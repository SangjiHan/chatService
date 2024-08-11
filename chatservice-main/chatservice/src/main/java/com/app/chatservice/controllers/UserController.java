package com.app.chatservice.controllers;

import java.net.http.HttpRequest;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    private Logger logger = Logger.getLogger(getClass().getName());

    @PostMapping("/join")
    public RedirectView joinUser(AppUserEntity entitiy){
        String redirectValue = service.joinUser(entitiy);
        return new RedirectView(redirectValue);
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String appuserEmail,
     @RequestParam String appuserPassword, @RequestParam(required = false) String keepLoggedIn,
     Model model) {
        logger.info(appuserEmail); 
        logger.info(appuserPassword); 
        logger.info(keepLoggedIn);

        AppUserEntity result = service.getLoginUser(appuserEmail, appuserPassword, keepLoggedIn);

        if(result != null){
        }
        return new RedirectView("/");
    }

    @GetMapping("/logout")
    public RedirectView logout(){
        service.logout();
        return new RedirectView("/");
    }

}
