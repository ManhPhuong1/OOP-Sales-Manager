package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.enums.PaymentStatus;

import java.sql.Timestamp;

public interface IOrderDetailInfo {

    Integer getId();

    String getCustomerName();

    String getPhone();

    Integer getCustomerId();

    Timestamp getCreatedAt();

    String getStaffName();

    PaymentStatus getPaymentStatus();

    Integer getAmount();

    Integer getDiscount();

    Integer getReturnOrderId();

    Integer getReturnAmount();
}
