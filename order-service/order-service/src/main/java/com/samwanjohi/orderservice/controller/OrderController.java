package com.samwanjohi.orderservice.controller;

import com.samwanjohi.orderservice.dto.OrderRequest;
import com.samwanjohi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return new ResponseEntity<>("Order placed successfully!!", HttpStatus.CREATED);
    }


}
