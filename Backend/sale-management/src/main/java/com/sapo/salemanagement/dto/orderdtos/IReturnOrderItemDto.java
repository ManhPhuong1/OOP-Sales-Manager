package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.enums.PaymentStatus;

import java.sql.Timestamp;

public interface IReturnOrderItemDto {

    Integer getReturnOrderId();

    Integer getBaseOrderId();

    Integer getSwapOrderId();

    String getCustomerName();

    Integer getAmount();

    Timestamp getCreatedAt();

    String getReturnReason();

    PaymentStatus getPaymentStatus();
}
