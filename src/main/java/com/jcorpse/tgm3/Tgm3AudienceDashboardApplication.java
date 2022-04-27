package com.jcorpse.tgm3;

import com.jcorpse.tgm3.bot.discord.DiscordBot;
import com.jcorpse.tgm3.bot.twitch.TwitchBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tgm3AudienceDashboardApplication {

    private static ApplicationContext appContext;

    public static void main(String[] args) {
         SpringApplication.run(Tgm3AudienceDashboardApplication.class, args);
        appContext.getBean(TwitchBot.class).run();
        appContext.getBean(DiscordBot.class).run();
//        TwitchBot.getInstance().test();
//        DiscordBot.getInstance().run();
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }
}
