package com.example.service;

import com.example.event.MessageSentEvent;
import com.example.event.PlusPointsClaimEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListener {
    private final ObjectMapper objectMapper;
    private final Set<Long> uniqueIds = new HashSet<>();

    @KafkaListener(topics = "messagingTopic")
    public void handleMessageSentEvent(String event) {
        try {
            MessageSentEvent messageSentEvent = objectMapper.readValue(event, MessageSentEvent.class);
            log.info("Received message: {}, from sender with id: {}", messageSentEvent.getMessage(), messageSentEvent.getSenderId());
        } catch (JsonProcessingException e) {
            log.error("Error deserializing Json: {}", e);
        }
    }

    @KafkaListener(topics = "plusPointsTopic")
    public void handlePlusPointsClaimEvent(String event) {
        try {
            PlusPointsClaimEvent plusPointsClaimEvent = objectMapper.readValue(event, PlusPointsClaimEvent.class);
            log.info("Received PlusPointsClaimEvent, with id: {}", plusPointsClaimEvent.getClaimId());

            if (uniqueIds.contains(plusPointsClaimEvent.getClaimId())) {
                log.info("Duplicate PlusPointsClaimEvent. Plus points with id: {} was already claimed", plusPointsClaimEvent.getClaimId());
            } else {
                uniqueIds.add(plusPointsClaimEvent.getClaimId());
                log.info("Plus points claimed!!!");
            }
        } catch (JsonProcessingException e) {
            log.error("Error deserializing Json: {}", e);
        }
    }
}
