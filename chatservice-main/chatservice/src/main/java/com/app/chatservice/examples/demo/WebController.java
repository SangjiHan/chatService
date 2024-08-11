package com.app.chatservice.examples.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/channels_main")
    public String channelsMain() {
        return "channels_main";
    }

    @GetMapping("/channels_server")
    public String channelsServer() {
        return "channels_server";
    }

    @GetMapping("/setting")
    public String setting() {
        return "setting";
    }
}