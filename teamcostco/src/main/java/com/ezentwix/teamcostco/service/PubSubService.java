package com.ezentwix.teamcostco.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import jakarta.annotation.PostConstruct;
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
            System.out.println("Project ID: " + projectId);
            System.out.println("Topic ID: " + topicId);

            TopicName topicName = TopicName.of(projectId, topicId);
            System.out.println(topicName);
            this.publisher = Publisher.newBuilder(topicName).build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Publisher instance", e);
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
