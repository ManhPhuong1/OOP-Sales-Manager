package com.sapo.salemanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_order")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "person_in_charge")
    @JsonBackReference
    private UserEntity userEntity;

    @Column(columnDefinition = "integer default 0")
    private int discount;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderLine> orderLineList;

    @OneToOne(mappedBy = "swapOrder")
    private ReturnOrder returnOrder;
}
