package com.example.stripePayment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequests {
    private String name;
    private Long amount;
    private Long quantity;
    private String currency;
} 
