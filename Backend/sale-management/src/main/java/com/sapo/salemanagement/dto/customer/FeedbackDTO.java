package com.sapo.salemanagement.dto.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {
    @Positive
    private Integer customerId;

    @NotNull
    private int evaluate;
    @NotNull
    @NotEmpty
    private String content;

}
