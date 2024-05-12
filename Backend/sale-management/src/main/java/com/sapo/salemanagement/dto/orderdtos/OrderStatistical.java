package com.sapo.salemanagement.dto.orderdtos;

import com.sapo.salemanagement.models.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class OrderStatistical {
    private Long numberProductsSold;
    private Long orderNumber;
    private Long revenue;
    private Date createdAt;
//    @ManyToOne
//    @JoinColumn(name = "person_in_charge")
//    private UserEntity userEntity;
    public OrderStatistical() {
    }

    public OrderStatistical(Long numberProductsSold, Long orderNumber, Long revenue, Date createdAt) {
        this.numberProductsSold = numberProductsSold;
        this.orderNumber = orderNumber;
        this.revenue = revenue;
        this.createdAt = createdAt;
    }


}
