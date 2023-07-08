package com.jcorpse.tgm3.bot.discord;

import com.jcorpse.tgm3.config.Constant;
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
            FishStoreCheater();
            Slot();
        } catch (Exception e) {
            log.error("DiscordBot start error : {}", e.getMessage());
        }
    }

    public void sendMsg(String content) {
        Client.getRestClient().getChannelById(Snowflake.of(Id.of(Constant.DISCORD_CHANNEl))).createMessage(content).subscribe();
    }

    public void sendMsg(String ChannelID, String content) {
        Client.getRestClient().getChannelById(Snowflake.of(Id.of(ChannelID))).createMessage(content).subscribe();
    }

    private static void FishStoreCheater() {
        Client.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (message.getContent().equalsIgnoreCase("!魚灘")) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("""
                        <:LadellAMon:867426295784538153><:LadellAMon:867426295784538153><:LadellAMon:867426295784538153><:LadellAMon:867426295784538153><:LadellAMon:867426295784538153><:LadellAMon:867426295784538153>
                        <:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872>
                        <:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872>
                        <:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872><:fishSABA2:950312669355855872>
                        <:fishSABA3:950312699517083668><:fishSABA3:950312699517083668><:fishSABA3:950312699517083668><:fishSABA3:950312699517083668><:fishSABA3:950312699517083668><:fishSABA3:950312699517083668>
                        """).block();
            }
        });
    }


    private static void Slot() {
        Client.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (message.getContent().equalsIgnoreCase("!sdslot")) {
                final MessageChannel channel = message.getChannel().block();
                String defSolt = """
                        [      **Sd Slot**      ]
                        -----------------
                        | %s | %s | %s |
                        | %s | %s | %s |
                        | %s | %s | %s |
                        -----------------
                         """;
                channel.createMessage(defSolt.formatted(Constant.getRandomEmojis(9))).block();
            }
        });
    }
}
