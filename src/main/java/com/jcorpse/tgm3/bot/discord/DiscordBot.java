package com.jcorpse.tgm3.bot.discord;

import com.jcorpse.tgm3.config.Constant;
import com.jcorpse.tgm3.org.cwb.Earthquake;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.Id;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscordBot {
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

    private static void init() {
        try {
            Client = DiscordClientBuilder.create(Constant.DISCORD_TOKEN)
                    .build()
                    .login()
                    .block();
            ReportEarthquake();
        } catch (Exception e) {
            log.error("DiscordBot start error : {}", e.getMessage());
        }
    }

    public void sendMsg(String content) {
        Client.getRestClient().getChannelById(Snowflake.of(Id.of(Constant.DISCORD_CHANNEl))).createMessage(content).subscribe();
    }

    private static void ReportEarthquake() {
        Client.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (message.getContent().equalsIgnoreCase("!震")) {
                final MessageChannel channel = message.getChannel().block();
                EmbedCreateSpec Embed = Earthquake.getEarthquakeReport();
                if (Embed != null) {
                    channel.createMessage(Embed).block();
                } else {
                    channel.createMessage("ㄅ欠，政府的API太廢，沒反應，你等等在試試吧 ㄏ").block();
                }
            }
        });
    }
}
