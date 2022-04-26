package com.jcorpse.tgm3.websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SessionManager {
    private static ConcurrentHashMap<String, WebSocketSession> MANAGER = new ConcurrentHashMap<String, WebSocketSession>();

    public static void add(String key, WebSocketSession session) {
        MANAGER.put(key, session);
    }

    public static void remove(String key) {
        MANAGER.remove(key);
    }

    public static WebSocketSession get(String key) {
        return MANAGER.get(key);
    }

    public static void broadcast(TextMessage msg) {
        MANAGER.forEach((key, session) -> {
            try {
                session.sendMessage(msg);
            } catch (IOException e) {
                log.error("broadcast error {}", e.getMessage());
            }
        });
    }

}
