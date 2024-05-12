package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.enums.PaymentStatus;

import java.sql.Timestamp;

public interface IReturnOrderDetailInfo {

    String getCustomerName();

    Integer getCustomerId();

    Integer getBaseOrderId();

    Timestamp getCreatedAt();

    Integer getSwapOrderId();

    Integer getSwapAmount();

    String getStaffName();

    String getReturnReason();

    PaymentStatus getPaymentStatus();
}
