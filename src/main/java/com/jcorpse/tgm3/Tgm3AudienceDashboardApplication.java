package com.jcorpse.tgm3;

import com.jcorpse.tgm3.bot.discord.DiscordBot;
import com.jcorpse.tgm3.bot.twitch.TwitchBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Tgm3AudienceDashboardApplication {

    public static void main(String[] args) {
         SpringApplication.run(Tgm3AudienceDashboardApplication.class, args);
    }

}
