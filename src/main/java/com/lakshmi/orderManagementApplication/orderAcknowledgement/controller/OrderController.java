package com.lakshmi.orderManagementApplication.orderAcknowledgement.controller;

import com.lakshmi.orderManagementApplication.orderAcknowledgement.model.OrderDetail;
import com.lakshmi.orderManagementApplication.orderAcknowledgement.service.OrderAcknowledgmentService;
import jakarta.jms.JMSException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NamingException;

@RestController
public class OrderController {
    @Autowired
    private OrderAcknowledgmentService orderService;

    @PostMapping(value="/orders")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderDetail order) throws NamingException, JMSException {
        String orderStatus = orderService.createOrder(order);
        return ResponseEntity.ok(orderStatus);
    }
}
