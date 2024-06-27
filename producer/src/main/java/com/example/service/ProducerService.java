package com.example.service;

import com.example.dto.MessageRequest;
import com.example.dto.PlusPointsClaimRequest;
import com.example.event.Event;
import com.example.event.MessageSentEvent;
import com.example.event.PlusPointsClaimEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private static long senderId = 1;
    private final KafkaTemplate<String, Event> kafkaTemplate;
    private static final String MESSAGING_TOPIC = "messagingTopic";
    private static final String PLUS_POINTS_TOPIC = "plusPointsTopic";

    public MessageSentEvent sendMessage(MessageRequest request) {
        var event = new MessageSentEvent(senderId++, request.getMessage());
        kafkaTemplate.send(MESSAGING_TOPIC, event);
        log.info("Message Sent to messagingTopic");
        return event;
    }

    public PlusPointsClaimEvent claimPlusPoints(PlusPointsClaimRequest request) {
        var event = new PlusPointsClaimEvent(request.getClaimId(), request.getClaimAmount());
        kafkaTemplate.send(PLUS_POINTS_TOPIC, event);
        log.info("Claim event sent to plusPointsTopic");
        return event;
    }
}
