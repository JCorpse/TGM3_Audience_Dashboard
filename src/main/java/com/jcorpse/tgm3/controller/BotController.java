package com.jcorpse.tgm3.controller;

import com.jcorpse.tgm3.bot.twitch.TwitchBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "/bot")
@Slf4j
public class BotController {

    @GetMapping(value = "/send")
    public String getBotSend() {
        return "send";
    }

    @PostMapping(value = "/send")
    public String Send(@RequestParam("token") String token,@RequestParam("msg") String msg) {
        if(token.equalsIgnoreCase(System.getenv("sendbot"))){
            TwitchBot.ChatSend(msg);
        }
        return "send";
    }
}
