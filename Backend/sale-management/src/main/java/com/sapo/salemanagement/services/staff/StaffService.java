package com.sapo.salemanagement.services.staff;

import com.sapo.salemanagement.dto.staff.CreateStaffDto;
import com.sapo.salemanagement.dto.staff.StaffItemDto;
import com.sapo.salemanagement.dto.staff.UpdateStaffDto;
import com.sapo.salemanagement.exceptions.NotFoundException;
import com.sapo.salemanagement.models.Role;
import com.sapo.salemanagement.models.UserEntity;
import com.sapo.salemanagement.models.enums.RoleType;
import com.sapo.salemanagement.models.enums.WorkStatus;
import com.sapo.salemanagement.repositories.RoleRepository;
import com.sapo.salemanagement.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public long countTotalStaffs() {
        return userRepository.count() - 1;
    }

    public List<StaffItemDto> getAllStaffs(int page, int size, String search) {
        ModelMapper modelMapper = new ModelMapper();
        Pageable paging = PageRequest.of(page, size);
        Page<UserEntity> staffListPage = userRepository.getAllStaffs(search, paging);
        List<UserEntity> staffList = staffListPage.getContent();
        return Arrays.asList(modelMapper.map(staffList, StaffItemDto[].class));
    }

    public void createStaff(CreateStaffDto createStaffDto) {
        createStaffDto.setPassword(passwordEncoder.encode(createStaffDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        List<Role> roles = new ArrayList<>();
        for (RoleType roleName: createStaffDto.getRoleNames()) {
            Role role = roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException("role not found"));
            roles.add(role);
        }
        UserEntity user = modelMapper.map(createStaffDto, UserEntity.class);
        user.setWorkStatus(WorkStatus.WORKING);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public UserEntity getStaffDetail(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
    }

    public void deleteStaff(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        user.setWorkStatus(WorkStatus.QUIT);
        userRepository.save(user);
    }

    public void updateStaffInfo(int id, UpdateStaffDto updateStaffDto) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        List<Role> roles = new ArrayList<>();
        for (RoleType roleName: updateStaffDto.getRoleNames()) {
            Role role = roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException("role not found"));
            roles.add(role);
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(updateStaffDto, user);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void updateStaffPassword(int id, String password) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
