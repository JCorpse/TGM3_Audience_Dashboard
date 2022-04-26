package com.jcorpse.tgm3.bot.discord;

import com.jcorpse.tgm3.config.Constant;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.Id;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscordBot{
    private static GatewayDiscordClient Client;

    static {
        run();
    }

    public static DiscordBot getInstance() {
        return DiscordBot.Holder.Instance;
    }
    private static class Holder {
        private static DiscordBot Instance = new DiscordBot();
    }

    public static void run() {
        init();
    }

    private static void init(){
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
