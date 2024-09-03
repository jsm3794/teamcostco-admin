package com.ezentwix.teamcostco.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PubSubService {
    private final WebSocketService webSocketService;

    @Value("${google.pubsub.project-id}")
    private String projectId;

    @Value("${google.pubsub.topic-id}")
    private String topicId;

    private Publisher publisher;

    @PostConstruct
    public void init() {
        try {
            // 클래스패스에서 JSON 파일을 읽어오기
            InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("ezen-project-434407-4ef43dc9dd82.json");
            if (credentialsStream == null) {
                throw new RuntimeException("Failed to find the credentials file in the classpath");
            }

            // ServiceAccountCredentials 객체 생성
            ServiceAccountCredentials credentials = ServiceAccountCredentials.fromStream(credentialsStream);

            // TopicName 객체 생성
            TopicName topicName = TopicName.of(projectId, topicId);

            // Publisher 객체 생성
            this.publisher = Publisher.newBuilder(topicName)
                    .setCredentialsProvider(() -> credentials)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Publisher instance", e);
        }
    }

    @PreDestroy
    public void shutdown() {
        if (publisher != null) {
            try {
                publisher.shutdown();
                publisher.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Publisher shutdown interrupted");
            }
        }
    }
    
    // PubSubService 클래스
    public void publishMessage(String message) {
        try {
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(ByteString.copyFromUtf8(message))
                    .build();

            ApiFuture<String> future = publisher.publish(pubsubMessage);
            String messageId = future.get();
            System.out.println("Published message ID: " + messageId);

            // WebSocket을 통해 클라이언트에게 알림 전송
            webSocketService.sendNotification(message);
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Failed to publish message to Pub/Sub: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to send WebSocket notification: " + e.getMessage());
        }
    }

}
