package com.jcorpse.tgm3.websocket;

import com.jcorpse.tgm3.dto.HyperTrain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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
        if(message.getPayload().equalsIgnoreCase("Jtest")){
            BroadcastTest();
        }else {
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

    private void BroadcastTest(){
        HyperTrain Train = new HyperTrain();
        Train.setTrainID("123456789");
        Train.setLastLevel(5);
        Train.setPercent("88");
        Train.setExpiresAt("2022-04-23 23:26:39");
        Train.setStartedAt("2022-04-23 23:31:39");
        Train.setEndAt("2022-04-23 23:31:39");
        Train.setGoal(1122348568);
        Train.setProgress(5566668);
        wsBroadcast(new TextMessage(Train.toString()));
    }
}
