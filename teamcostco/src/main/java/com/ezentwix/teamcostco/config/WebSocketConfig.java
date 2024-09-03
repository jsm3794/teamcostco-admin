package com.ezentwix.teamcostco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ezentwix.teamcostco.websocket.NotificationHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(notificationHandler(), "/notifications")
                .setAllowedOrigins("*");
    }

    @Bean
    public NotificationHandler notificationHandler() {
        return new NotificationHandler();
    }
}
