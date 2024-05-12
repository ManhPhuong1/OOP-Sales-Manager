package com.sapo.salemanagement.dto.orderdtos;

import java.sql.Timestamp;

public interface IReturnHistoryItemDto {

    Integer getReturnOrderId();

    Timestamp getCreatedAt();

    Integer getReturnQuantity();

    Integer getReturnValue();

    Integer getSwapOrderId();

    Integer getSwapQuantity();

    Integer getSwapValue();
}
