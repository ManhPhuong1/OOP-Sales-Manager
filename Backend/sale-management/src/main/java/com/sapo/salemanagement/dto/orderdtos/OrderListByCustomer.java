package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderListByCustomer {
    private Order order;

    private Payment payment;

    public static OrderListByCustomer from(Order order) {
        return OrderListByCustomer.builder()
                .order(order)
                .build();
    }

}
