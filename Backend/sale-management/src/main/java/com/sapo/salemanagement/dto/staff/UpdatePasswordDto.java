package com.sapo.salemanagement.dto.staff;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordDto {

    @NotBlank
    private String password;
}
