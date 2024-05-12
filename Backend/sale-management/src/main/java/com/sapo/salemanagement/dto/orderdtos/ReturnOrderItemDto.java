package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReturnOrderItemDto {

    private Integer returnOrderId;

    private Integer baseOrderId;

    private Integer swapOrderId;

    private String customerName;

    private Integer amount;

    private Timestamp createdAt;

    private String returnReason;

    private PaymentStatus paymentStatus;
}
