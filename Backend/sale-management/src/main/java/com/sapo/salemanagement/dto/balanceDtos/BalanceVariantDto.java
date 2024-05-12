package com.sapo.salemanagement.dto.balanceDtos;

import com.sapo.salemanagement.dto.productDtos.VariantDto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceVariantDto {
    @NotNull(message = "variantId is not null")
    @Pattern(regexp = "^[0-9]*$", message = "Should only contain digits 0 to 9")
    private int variantId;
    private int warehouseBalanceId;
    @NotNull(message = "realQ is not null")
    @Pattern(regexp = "^[0-9]*$", message = "Should only contain digits 0 to 9")
    private int realQ;

    private int savedQ;

    private String note;

    private VariantDto variant;

}
