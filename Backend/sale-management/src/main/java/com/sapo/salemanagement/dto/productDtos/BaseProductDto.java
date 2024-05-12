package com.sapo.salemanagement.dto.productDtos;

import com.sapo.salemanagement.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class BaseProductDto extends BaseDto {
    @NotNull(message = "Tên sản phẩm không được trống")
    @NotBlank(message = "Tên sản phẩm không được trống")
    private String name;

    private String label;
    private int variantNumber;
    private int quantity;
    @NotNull(message = "Thuộc tính không được trống")
    @NotBlank(message = "Thuộc tính không được trống")
    private String attribute1;

    private String attribute2;

    private String attribute3;
    @NotEmpty(message = "Phải có ít nhất 1 phiên bản")
    private List<VariantDto> variants;


}
