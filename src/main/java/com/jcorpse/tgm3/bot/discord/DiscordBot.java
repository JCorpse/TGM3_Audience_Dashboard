package com.jcorpse.tgm3.bot.discord;

import com.jcorpse.tgm3.config.Constant;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DiscordBot{
    private GatewayDiscordClient Client;

    public void run() {
        init();
    }

    private void init(){
        try {
            Client = DiscordClientBuilder.create(Constant.DISCORD_TOKEN)
                    .build()
                    .login()
                    .block();
        } catch (Exception e) {
            log.error("DiscordBot start error : {}",e.getMessage());
        }
    }

    public void sendMsg(String content) {
        Client.getRestClient().getChannelById(Snowflake.of(Id.of(Constant.DISCORD_CHANNEl))).createMessage(content).subscribe();
    }
}
