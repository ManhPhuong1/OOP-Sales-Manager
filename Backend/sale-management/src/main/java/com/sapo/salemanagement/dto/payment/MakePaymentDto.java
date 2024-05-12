package com.sapo.salemanagement.dto.payment;

import com.sapo.salemanagement.models.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class MakePaymentDto {

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Timestamp createdAt;

    private Integer returnOrderId;

    private Integer swapOrderId;
}
