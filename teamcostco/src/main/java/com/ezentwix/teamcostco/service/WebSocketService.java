package com.ezentwix.teamcostco.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final Set<WebSocketSession> sessions = new HashSet<>();

    public synchronized void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public synchronized void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public synchronized void sendNotification(String message) throws IOException {
        System.out.println("Sending notification: " + message); // 로그 추가
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
