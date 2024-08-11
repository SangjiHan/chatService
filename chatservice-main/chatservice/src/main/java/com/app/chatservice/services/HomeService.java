package com.app.chatservice.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.app.chatservice.entities.ChannelEntity;
import com.app.chatservice.entities.ServerEntity;
import com.app.chatservice.entities.ServerMemberEntity;
import com.app.chatservice.entities.ServerMemberId;
import com.app.chatservice.processors.LoginProcessor;
import com.app.chatservice.sessionscoped.SessionId;
import com.app.chatservice.superentities.DisplayMessage;

import jakarta.servlet.http.Cookie;

@Service
public class HomeService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private LoginProcessor loginProcessor;

    @Autowired
    private SessionId sessionId;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ServerMemberService serverMemberService;

    @Autowired 
    private DisplayMessageService displayMessageService;

    public void putServerList(Model model){
        if(sessionId.getSessionId() == null){
            return;
        }

        List<ServerEntity> serverList = serverService.listByUser(sessionId.getSessionId());
        model.addAttribute("serverList", serverList);
    }

    public String managePage(String viewName, Cookie loginId, Cookie keepLoggedIn, Model model){

        loginProcessor.processLogin(loginId, keepLoggedIn ,true);

        if (sessionId.getSessionId() == null) {
            logger.info("login failed");
            return "index";
        }

        model.addAttribute("sessionId", sessionId.getSessionId());
        return viewName;
    }

    public String managePage(String viewName, Cookie loginId, Model model) {
        return managePage(viewName, loginId, null, model);
    }

    public void putChannelList(Model model, String serverId, String channelId) {
        if(sessionId.getSessionId() == null || serverId == null){
            return;
        }

        List<ChannelEntity> channelList = channelService.listByServer(serverId);
        ServerEntity currentServer = serverService.byId(serverId);
        model.addAttribute("channelList", channelList);
        model.addAttribute("currentServer", currentServer);
        model.addAttribute("currentChannelId", channelId);

        ChannelEntity channelEntity = channelService.findById(channelId);

        model.addAttribute("channelName", channelEntity.getChannelName());
    }

    public ServerMemberEntity checkIfUserIsAMember(String serverId) {
        ServerMemberId serverMemberId = new ServerMemberId(sessionId.getSessionId(), serverId);
        ServerMemberEntity serverMemberEntity = serverMemberService.findByServerMemberId(serverMemberId);

        return serverMemberEntity;
    }

    public void checkIfServerOwner(Model model, String serverId) {

        ServerEntity entity = serverService.findByServerIdAndServerOwner(serverId, sessionId.getSessionId());

        if(entity != null){
            model.addAttribute("isServerOwner", true);
        }else{
            model.addAttribute("isServerOwner", false);
        }
    }

    public String getFeaturedChannel(String serverId) {
        String featuredChannel = channelService.featuredChannel(serverId);
        return featuredChannel;
    }

    public void putMessages(Model model, String channelId) {
        List<DisplayMessage> displayMessages = (List<DisplayMessage>) displayMessageService.findLatest(channelId);

        for(DisplayMessage displayMessage : displayMessages){
            logger.info(displayMessage.getDisplayMessageEntity().getDisplayMessageId());
        }
        model.addAttribute("messages", displayMessages);
    }
}
