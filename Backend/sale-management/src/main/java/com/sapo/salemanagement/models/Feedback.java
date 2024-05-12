package com.sapo.salemanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback extends BaseEntity {

    private int evaluate;

    private String content;

    @ManyToOne
    @JoinColumn(name = "person_in_charge")
//    @JsonIgnore
//    @JsonIgnoreProperties("feedbacks")
    @JsonBackReference
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
//    @JsonIgnore
    @JsonBackReference
    private Customer customer;
}
