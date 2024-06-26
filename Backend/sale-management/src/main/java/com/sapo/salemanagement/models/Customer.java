package com.sapo.salemanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sapo.salemanagement.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {
    @NotNull(message = "name is not null")
    private String name;

    private String customerCode;

    private String address;

    private String email;

    private Date dateOfBirth;

    @NotNull(message = "phone is not null")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "group_id")
//    @JsonIgnore
    private CustomerGroup group;

    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private List<Feedback> feedbackList;
}
