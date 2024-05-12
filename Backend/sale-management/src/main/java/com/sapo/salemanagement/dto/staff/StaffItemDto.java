package com.sapo.salemanagement.dto.staff;

import com.sapo.salemanagement.models.Role;
import com.sapo.salemanagement.models.enums.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class StaffItemDto {

    private int id;

    private String fullName;

    private String phone;

    private WorkStatus workStatus;

    private List<Role> roles;

    private Timestamp createdAt;
}
