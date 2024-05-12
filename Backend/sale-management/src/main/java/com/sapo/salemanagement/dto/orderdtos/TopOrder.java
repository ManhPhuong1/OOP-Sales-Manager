package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.Variant;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class TopOrder {
    private Variant variant;
    private BigDecimal value;

    public TopOrder(Variant variant, BigDecimal value) {
        this.variant = variant;
        this.value = value;
    }
}
