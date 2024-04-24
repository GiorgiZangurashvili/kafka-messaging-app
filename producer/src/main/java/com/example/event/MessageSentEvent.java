package com.example.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageSentEvent implements Event{
    private long senderId;
    private String message;
}
