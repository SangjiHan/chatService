package com.app.chatservice.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.app.chatservice.entities.ServerMemberEntity;
import com.app.chatservice.processors.LoginProcessor;
import com.app.chatservice.services.ChannelService;
import com.app.chatservice.services.HomeService;
import com.app.chatservice.services.ServerMemberService;
import com.app.chatservice.sessionscoped.SessionId;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
  private Logger logger = Logger.getLogger(getClass().getName());

  @Autowired 
  private HomeService service;

  @Autowired
  private ChannelService channelService;

  @Autowired
  private ServerMemberService serverMemberService;

  @Autowired
  private SessionId sessionId;

  @GetMapping("/")
  public String home(Model model, @CookieValue(name = "loginId", required = false) Cookie loginId, 
    @CookieValue(name = "keepLoggedIn", required = false) Cookie keepLoggedIn) {
    String result = service.managePage("channels_main", loginId, keepLoggedIn, model);
    service.putServerList(model);
    model.addAttribute("loginAttempted", sessionId.isLoginAttempted());
    return result;
  }


  @GetMapping("settings")
  public String settings(Model model, @CookieValue(name = "loginId", required = false) Cookie loginId){
    return service.managePage("setting", loginId, model);
  }

  @GetMapping("/server/{serverId}")
  public Object goToServer(Model model, @PathVariable String serverId,
   @CookieValue(name = "loginId", required = false) Cookie loginId){
    //String result = service.managePage("channels_server", loginId, model);
    Object result = service.checkIfUserIsAMember(serverId); // 유저가 서버에 조인한 상태인지 체크

    if(result == null){
      return ResponseEntity.notFound().build(); // 없으면 에러 표시
    }

    String lastVisited = serverMemberService.lastVisitedByUserAndServer(serverId, sessionId.getSessionId());

    String url = "/server/" + serverId + "/channel/" + lastVisited;
    logger.info("lastvisited: " + lastVisited);
    return new RedirectView(url);
   }

   @GetMapping("server/{serverId}/channel/{channelId}")
   public Object goToChannel(@PathVariable String serverId, @PathVariable String channelId, Model model, 
   @CookieValue(name = "loginId", required = false) Cookie loginId){
      logger.info("channel");

      boolean channelExist = channelService.channelExist(channelId);

      if(channelExist){
        serverMemberService.updateLastVisited(serverId, sessionId.getSessionId(), channelId);
      }else{
        String lastVisited = channelService.findDefaultChannel(serverId);
        channelId = lastVisited;
      }

      service.putServerList(model);
      service.putChannelList(model, serverId, channelId);
      service.checkIfServerOwner(model, serverId);

      String result = service.managePage("channels_server", loginId, model);
      return result;
   }

}
