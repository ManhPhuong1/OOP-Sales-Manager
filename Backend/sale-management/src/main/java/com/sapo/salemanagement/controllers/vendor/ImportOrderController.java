package com.sapo.salemanagement.controllers.vendor;

import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.vendorDtos.ImportOrderDTO;
import com.sapo.salemanagement.services.vendor.impl.ImportOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/import")
public class ImportOrderController {
    private ImportOrderService importOrderService;

    ImportOrderController(ImportOrderService importOrderService){
        this.importOrderService = importOrderService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getImportOrderList(){
        List<ImportOrderDTO> importOrderDTOList = importOrderService.findAll();
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(importOrderDTOList)
                .build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> getImportOrderById(@PathVariable Integer id){
        ImportOrderDTO importOrderDTOList = importOrderService.findById(id);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(importOrderDTOList)
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createImportOrder(@RequestBody @Valid ImportOrderDTO importOrderDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String phoneUser = userDetails.getUsername();
        ImportOrderDTO importOrderDTO1 = importOrderService.save(importOrderDTO, phoneUser);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(importOrderDTO1)
                .message("success")
                .responseCode(200)
                .build());
    }

    @PutMapping(value= "/{id}")
    public ResponseEntity<ResponseObject> upadateImportOrder(@RequestBody @Valid ImportOrderDTO importOrderDTO, @PathVariable int id, @AuthenticationPrincipal UserDetails userDetails){
        String phoneUser = userDetails.getUsername();
        importOrderDTO.setId(id);
        ImportOrderDTO importOrderDTO1 = importOrderService.save(importOrderDTO, phoneUser);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(importOrderDTO1)
                .message("success")
                .responseCode(200)
                .build());
    }
}
