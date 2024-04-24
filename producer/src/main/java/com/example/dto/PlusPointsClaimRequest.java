package com.example.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlusPointsClaimRequest {
    private long claimId;
    private double claimAmount;
}
