package com.example.stripePayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class productResponse {
    private String Status;
    private String sessionID;
    private String sessionURL;
    private String message;
} // sessionID, sessionURL, message
