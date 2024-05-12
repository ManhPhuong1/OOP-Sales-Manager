package com.sapo.salemanagement.dto.orderdtos.createorder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVariant {

    @Positive
    @NotNull
    private int variantId;

    @Positive
    @NotNull
    private int quantity;
}
