package com.sapo.salemanagement.dto.vendorDtos;

import com.sapo.salemanagement.dto.BaseDto;
import com.sapo.salemanagement.dto.productDtos.VariantDto;
import com.sapo.salemanagement.models.UserEntity;
import com.sapo.salemanagement.models.enums.ShipmentStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportOrderDTO extends BaseDto {


    @NotNull(message = "Vendor cannot be null")
    private VendorDTO vendor;
    @NotEmpty(message = "Variant list cannot be empty")
    private List<VariantDto> variantDTOList;
    private String staffName;
    private ShipmentStatus shipmentStatus;
    private PaymentDTO paymentDTO;
}
