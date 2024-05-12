package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OrderListItemDto {

    private int orderId;

    private Timestamp createdAt;

    private String customerName;

    private String phone;

    private PaymentStatus paymentStatus;

    private int amount;
}
