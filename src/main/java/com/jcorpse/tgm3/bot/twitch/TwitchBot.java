package com.jcorpse.tgm3.bot.twitch;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.*;
import com.github.twitch4j.helix.domain.UserList;
import com.github.twitch4j.pubsub.events.*;
import com.jcorpse.tgm3.bot.discord.DiscordBot;
import com.jcorpse.tgm3.config.Constant;
import com.jcorpse.tgm3.dao.TwitchDao;
import com.jcorpse.tgm3.dao.impl.HyperTrainDaoImpl;
import com.jcorpse.tgm3.dto.HyperTrain;
import com.jcorpse.tgm3.websocket.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

@Component
@Slf4j
public class TwitchBot {
    private static OAuth2Credential Credential = new OAuth2Credential("twitch", Constant.TWITCH_OAUTH);
    private static DiscordBot Discord_Bot = DiscordBot.getInstance();
    private static DateTimeFormatter Formatter;
    private static TwitchClient Client;
    private static HyperTrain Train = new HyperTrain();
    private static ObjectMapper mapper = new ObjectMapper();


    @Autowired
    static
    TwitchDao twitchDao = new HyperTrainDaoImpl();


    static {
        run();
    }

    public static void run() {
        init();
        HypeTrainlistener();
        Chatlistener();
//        getChannelId(Constant.TWITCH_CHANNEL);
    }

    private static void init() {
        Client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(Credential)
                .withEnablePubSub(true)
                .withEnableHelix(true)
                .withDefaultAuthToken(Credential)
                .build();
        Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(Locale.TAIWAN)
                .withZone(ZoneId.of("Asia/Taipei"));
        mapper.registerModule(new JavaTimeModule());
    }

    public static void HypeTrainlistener() {
        Client.getPubSub().listenForHypeTrainEvents(Credential, Constant.TWITCH_CHANNEL_ID);

        Client.getEventManager().onEvent(HypeTrainApproachingEvent.class, (Event) -> {
            log.info(Event.toString());
        });

        Client.getEventManager().onEvent(HypeTrainStartEvent.class, (Event) -> {
            log.info(Event.toString());
            Train = new HyperTrain();
            Train.setTrainID(Event.getData().getId());
            Train.setStartedAt(Event.getData().getStartedAt().atZone(ZoneId.of("Asia/Taipei")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Train.setExpiresAt(Event.getData().getExpiresAt().atZone(ZoneId.of("Asia/Taipei")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            log.info(Event.getData().getId());
            Discord_Bot.sendMsg(
                    "==== 列車發車記錄(v2.0) ====\n" +
                            "開車時間: " + Train.getStartedAt() + "\n" +
                            "預計離站: " + Train.getExpiresAt() + "\n" +
                            "<@&806592766347968594>\n" +
                            "=======================");

            try {
                WebSocket.wsBroadcast(new TextMessage(mapper.writeValueAsString(Event.getData())));
            } catch (JsonProcessingException e) {
                log.error("wsBroadcast error {}",e.getMessage());
            }
        });

        Client.getEventManager().onEvent(HypeTrainProgressionEvent.class, (Event) -> {
            log.info(Event.toString());
            Train.setLastLevel(Event.getData().getProgress().getLevel().getValue());
            Train.setProgress(Event.getData().getProgress().getValue());
            Train.setGoal(Event.getData().getProgress().getGoal());
            Train.setPercent((new DecimalFormat("#.##").format(((float) Train.getProgress() / Train.getGoal()) * 100)));
        });
        Client.getEventManager().onEvent(HypeTrainLevelUpEvent.class, (Event) -> {
            log.info(Event.toString());
            Train.setLastLevel(Event.getData().getProgress().getLevel().getValue());
            Train.setProgress(Event.getData().getProgress().getValue());
            Train.setGoal(Event.getData().getProgress().getGoal());
            try {
                WebSocket.wsBroadcast(new TextMessage(mapper.writeValueAsString(Event.getData())));
            } catch (JsonProcessingException e) {
                log.error("wsBroadcast error {}",e.getMessage());
            }
        });
        Client.getEventManager().onEvent(HypeTrainEndEvent.class, (Event) -> {
            log.info(Event.toString());
            Instant EndTime = Event.getData().getEndedAt();
            Train.setEndAt(Formatter.format(EndTime));
            Discord_Bot.sendMsg(
                    "==== 列車發車記錄(v2.0) ====\n" +
                            "列車離站時間: " + Train.getEndAt() + "\n" +
                            "貼圖等級: " + Train.getLastLevel() + "-" + Train.getPercent() + "%\n" +
                            "=======================");
            try {
                WebSocket.wsBroadcast(new TextMessage(mapper.writeValueAsString(Event.getData())));
            } catch (JsonProcessingException e) {
                log.error("wsBroadcast error {}",e.getMessage());
            }
            twitchDao.save(Train);
        });
//        Client.getEventManager().onEvent(HypeTrainConductorUpdateEvent.class, (Event) -> {
//            log.info(Event.toString());
//        });
//        Client.getEventManager().onEvent(HypeTrainCooldownExpirationEvent.class, (Event) -> {
//            log.info(Event.toString());
//        });
    }

    private static void Chatlistener() {
        Client.getChat().joinChannel(Constant.TWITCH_CHANNEL);
        Client.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            log.info("[{}] {}:{}", event.getChannel().getName(), event.getUser().getName(), event.getMessage());
        });
    }

    public static void ChatSend(String Msg) {
        Client.getChat().sendMessage(Constant.TWITCH_CHANNEL, Msg);
    }

    public static void getChannelId(String ChannelName) {
        UserList resultList = Client.getHelix().getUsers(null, null, Arrays.asList(ChannelName)).execute();
        resultList.getUsers().forEach(user -> {
            log.info(user.getId());
        });
    }

}
