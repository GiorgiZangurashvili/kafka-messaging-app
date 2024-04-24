package com.example.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlusPointsClaimEvent implements Event {
    private long claimId;
    private double claimAmount;
}
