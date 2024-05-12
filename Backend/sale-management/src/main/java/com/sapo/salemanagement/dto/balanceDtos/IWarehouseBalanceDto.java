package com.sapo.salemanagement.dto.balanceDtos;

import java.sql.Timestamp;

public interface IWarehouseBalanceDto {
    int getId();
    Timestamp getCreatedAt();
    Timestamp getUpdatedAt();
    String getPersonInCharge();
    String getNote();
}
