package com.sapo.salemanagement.controllers.staff;

import com.sapo.salemanagement.dto.PagedResponseObject;
import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.staff.CreateStaffDto;
import com.sapo.salemanagement.dto.staff.StaffItemDto;
import com.sapo.salemanagement.dto.staff.UpdatePasswordDto;
import com.sapo.salemanagement.dto.staff.UpdateStaffDto;
import com.sapo.salemanagement.models.UserEntity;
import com.sapo.salemanagement.services.staff.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/staffs")
@Validated
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public ResponseEntity<PagedResponseObject> getAllStaffs(@RequestParam(value = "page", defaultValue = "0") @Valid int page,
                                                            @RequestParam(value = "size", defaultValue = "10") @Valid int size,
                                                            @RequestParam(defaultValue = "") String search) {
        long totalItems = staffService.countTotalStaffs();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<StaffItemDto> staffs = staffService.getAllStaffs(page, size, search);
        return ResponseEntity.ok(PagedResponseObject.builder()
                .page(page)
                .perPage(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(staffs)
                .message("Success")
                .responseCode(200)
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createStaff(@RequestBody @Valid CreateStaffDto createStaffDto) {
        staffService.createStaff(createStaffDto);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .responseCode(200)
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getStaffDetail(@PathVariable Integer id) {
        UserEntity user = staffService.getStaffDetail(id);
        return ResponseEntity.ok(ResponseObject.builder()
                        .data(user)
                        .message("success")
                        .responseCode(200)
                        .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .responseCode(200)
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseObject> updateStaffInfo(@PathVariable Integer id, @RequestBody @Valid UpdateStaffDto updateStaffDto) {
        staffService.updateStaffInfo(id, updateStaffDto);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .responseCode(200)
                .build());
    }

    @PutMapping("{id}/password")
    public ResponseEntity<ResponseObject> updateStaffPassword(@PathVariable Integer id, @RequestBody @Valid UpdatePasswordDto updatePasswordDto) {
        staffService.updateStaffPassword(id, updatePasswordDto.getPassword());
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .responseCode(200)
                .build());
    }
}
