package com.example.controller;

import com.example.dto.MessageRequest;
import com.example.dto.PlusPointsClaimRequest;
import com.example.event.MessageSentEvent;
import com.example.event.PlusPointsClaimEvent;
import com.example.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produce")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<MessageSentEvent> sendEvent(@RequestBody MessageRequest request) {
        Optional<MessageSentEvent> event = Optional.of(producerService.sendMessage(request));
        return ResponseEntity.of(event);
    }

    @PostMapping("/plusPoints")
    public ResponseEntity<PlusPointsClaimEvent> claimPlusPoints(@RequestBody PlusPointsClaimRequest request) {
        Optional<PlusPointsClaimEvent> event = Optional.of(producerService.claimPlusPoints(request));
        return ResponseEntity.of(event);
    }

}
