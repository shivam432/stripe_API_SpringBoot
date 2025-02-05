package com.example.stripePayment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.stripePayment.dto.ProductRequests;
import com.example.stripePayment.dto.productResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripeService {
    @Value("${stripe.secretKey}")
    private String secretKey;

    public productResponse checkoutProducts(ProductRequests request){
        Stripe.apiKey=secretKey;

        // System.out.println("Hey there i am the secret key->"+secretKey);
        
        SessionCreateParams.LineItem.PriceData.ProductData productData= SessionCreateParams.LineItem.PriceData.ProductData
                .builder().setName(request.getName()).build();
        
        SessionCreateParams.LineItem.PriceData priceData= SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(request.getCurrency() != null?request.getCurrency():"USD")
                .setUnitAmount(request.getAmount()).setProductData(productData).build();

        SessionCreateParams.LineItem lineItem=SessionCreateParams.LineItem
                .builder().setQuantity(request.getQuantity()).setPriceData(priceData).build();
        
        SessionCreateParams params=SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(lineItem)
                .build();
        
        Session session = null;

        try {
            session= Session.create(params);

            return productResponse.builder()
                .Status("Success")
                .message("Payment Session Created")
                .sessionID(session.getId())
                .sessionURL(session.getUrl())
                .build();
        } catch (StripeException e) {
            System.out.println(e.getMessage());

            return productResponse.builder()
                    .Status("Error")
                    .message(e.getMessage())
                    .build();
        }
    }
}
