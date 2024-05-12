package com.sapo.salemanagement.controllers.product;

import com.sapo.salemanagement.controllers.BaseController;
import com.sapo.salemanagement.dto.PagedResponseObject;
import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.productDtos.VariantDto;
import com.sapo.salemanagement.services.product.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class VariantController extends BaseController {
    @Autowired
    private VariantService variantService;

    @GetMapping("/base-products/variants")
    public ResponseEntity<PagedResponseObject> getAllVariants(@RequestParam(name = "page", defaultValue = "1") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        long totalItems = variantService.countVariant();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<VariantDto> variantDtos = variantService.getAllVariants(page, size);
        return ResponseEntity.ok(PagedResponseObject.builder()
                        .page(page)
                        .perPage(size)
                        .totalPages(totalPages)
                        .totalItems(totalItems)
                        .responseCode(200)
                        .message("Success")
                        .data(variantDtos)
                        .build());
    }

    @GetMapping("base-products/variants/search")
    public ResponseEntity<ResponseObject> getAllVariantsByKeyword(@RequestParam(name = "keyword") String keyword) {

        List<VariantDto> variantDtos = variantService.getAllVariantsByKeyword(keyword);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("Success")
                .data(variantDtos)
                .build());
    }


}
