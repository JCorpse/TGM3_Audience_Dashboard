package com.jcorpse.tgm3.controller;

import com.jcorpse.tgm3.bot.twitch.TwitchBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping(value = "/bot")
public class BotController {

    @GetMapping(value = "/send")
    public String getBotSend() {
        return "send";
    }

    @CrossOrigin
    @PostMapping(value = "/send")
    public void Send(@RequestParam("token") String token,@RequestParam("msg") String msg) {
        if(token.equalsIgnoreCase(System.getenv("sendbot"))){
            TwitchBot.ChatSend(msg);
        }
    }
}
