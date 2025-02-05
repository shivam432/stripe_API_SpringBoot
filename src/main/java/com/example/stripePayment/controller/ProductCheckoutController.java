package com.example.stripePayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stripePayment.dto.ProductRequests;
import com.example.stripePayment.dto.productResponse;
import com.example.stripePayment.service.StripeService;

@RestController
@RequestMapping("/product/v1")
public class ProductCheckoutController {

    @Autowired
    private StripeService stripeService;

    public ProductCheckoutController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<productResponse> checkoutProducts(@RequestBody ProductRequests requests){
        productResponse response= stripeService.checkoutProducts(requests);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
