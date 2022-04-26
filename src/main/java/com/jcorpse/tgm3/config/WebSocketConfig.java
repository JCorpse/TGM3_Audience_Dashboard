package com.jcorpse.tgm3.config;


import com.jcorpse.tgm3.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Component
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocket ws;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ws,"ws").setAllowedOrigins("*");
    }
}
