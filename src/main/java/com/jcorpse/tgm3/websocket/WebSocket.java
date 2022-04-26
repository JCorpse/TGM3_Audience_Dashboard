package com.jcorpse.tgm3.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.twitch4j.pubsub.domain.*;
import com.github.twitch4j.pubsub.events.HypeTrainLevelUpEvent;
import com.github.twitch4j.pubsub.events.HypeTrainProgressionEvent;
import com.github.twitch4j.pubsub.events.HypeTrainStartEvent;
import com.jcorpse.tgm3.config.Constant;
import com.jcorpse.tgm3.dto.HyperTrain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
public class WebSocket extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} connect", session.getLocalAddress());
        SessionManager.add(session.getLocalAddress().getHostString(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        switch (message.getPayload()){
            case "starttest":
                startTest();
                break;
            case  "ptest":
                pTest();
                break;
            case  "lvtest":
                lvTest();
                break;
            default:
                session.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} close status:{}", session.getLocalAddress(), status.getCode());
        SessionManager.remove(session.getLocalAddress().getHostString());
    }


    public static void wsBroadcast(TextMessage message){
        SessionManager.broadcast(message);
    }

    private void startTest() throws JsonProcessingException {
        HypeTrainStart hypeTrainStart = new HypeTrainStart();
        hypeTrainStart.setId(UUID.randomUUID().toString());
        hypeTrainStart.setStartedAt(Instant.now());
        hypeTrainStart.setChannelId(Constant.TWITCH_CHANNEL_ID);
        hypeTrainStart.setExpiresAt(Instant.now());
        HypeTrainStartEvent startEvent = new HypeTrainStartEvent(hypeTrainStart);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(startEvent);
        wsBroadcast(new TextMessage(json));
    }

    private void pTest() throws JsonProcessingException {
        HypeTrainProgress hypeTrainProgress = new HypeTrainProgress();
        hypeTrainProgress.setGoal(18000);
        hypeTrainProgress.setTotal(900000);
        hypeTrainProgress.setValue(50);
        hypeTrainProgress.setRemainingSeconds(50);
        HypeProgression progression = new HypeProgression();
        progression.setProgress(hypeTrainProgress);
        progression.setAction("pTest");
        progression.setQuantity(99);
        progression.setSource("1122");
        progression.setSequenceId(1123);
        progression.setUserId("123");
        HypeTrainProgressionEvent progressionEvent = new HypeTrainProgressionEvent(Constant.TWITCH_CHANNEL_ID,progression);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(progressionEvent);
        wsBroadcast(new TextMessage(json));
    }

    private void lvTest() throws JsonProcessingException {
        HypeTrainProgress hypeTrainProgress = new HypeTrainProgress();
        hypeTrainProgress.setGoal(18000);
        hypeTrainProgress.setTotal(900000);
        hypeTrainProgress.setValue(50);
        hypeTrainProgress.setRemainingSeconds(50);
        HypeLevelUp levelUp = new HypeLevelUp();
        levelUp.setProgress(hypeTrainProgress);
        levelUp.setTimeToExpire(Instant.now());
        HypeTrainLevelUpEvent levelUpEvent = new HypeTrainLevelUpEvent(Constant.TWITCH_CHANNEL_ID,levelUp);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(levelUpEvent);
        wsBroadcast(new TextMessage(json));
    }
}
