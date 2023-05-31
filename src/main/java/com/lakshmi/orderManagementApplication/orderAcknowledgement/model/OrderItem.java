package com.lakshmi.orderManagementApplication.orderAcknowledgement.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderItem implements Serializable {
    private String productId;
    private String productName;
    private double unitPrice;
    private int quantity;
    private double subtotal;
}
