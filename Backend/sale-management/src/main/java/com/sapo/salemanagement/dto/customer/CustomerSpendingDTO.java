package com.sapo.salemanagement.dto.customer;

import com.sapo.salemanagement.models.Customer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class CustomerSpendingDTO {
    @NotNull
    private Customer customer;
    @NotNull
    private BigDecimal totalAmount;
    @NotNull
    private BigDecimal orderCount;

    public CustomerSpendingDTO(Customer customer, BigDecimal totalAmount, BigDecimal orderCount) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.orderCount = orderCount;
    }

}
