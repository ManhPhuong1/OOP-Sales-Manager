package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.enums.PaymentStatus;

import java.sql.Timestamp;

public interface IOrderListItemDto {

    int getOrderId();

    Timestamp getCreatedAt();

    String getCustomerName();

    String getPhone();

    PaymentStatus getPaymentStatus();

    int getAmount();
}
