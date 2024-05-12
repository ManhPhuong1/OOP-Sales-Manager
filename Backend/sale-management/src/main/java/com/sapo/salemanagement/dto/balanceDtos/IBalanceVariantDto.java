package com.sapo.salemanagement.dto.balanceDtos;

public interface IBalanceVariantDto {
    int getVariantId();
    int getWarehouseBalanceId();

    int getRealQ();

    int getSavedQ();

    String getNote();
}
