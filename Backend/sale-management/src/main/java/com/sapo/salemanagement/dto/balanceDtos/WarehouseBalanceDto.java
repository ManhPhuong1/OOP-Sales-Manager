package com.sapo.salemanagement.dto.balanceDtos;

import com.sapo.salemanagement.dto.BaseDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class WarehouseBalanceDto extends BaseDto {
    private String personInCharge;
    @NotEmpty(message = "Danh sách sản phẩm không được trống")
    private List<BalanceVariantDto> balanceVariantList;
    private String note;
}
