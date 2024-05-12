package com.sapo.salemanagement.dto.auth;

import com.sapo.salemanagement.models.Role;
import com.sapo.salemanagement.models.enums.Gender;
import com.sapo.salemanagement.models.enums.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UserInfoDto {

    private String fullName;

    private boolean isActive;

    private String phone;

    private String address;

    private Date dob;

    private Gender gender;

    private WorkStatus workStatus;

    private List<Role> roles;
}
