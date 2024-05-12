package com.sapo.salemanagement.controllers.order;

import com.sapo.salemanagement.dto.PagedResponseObject;
import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.orderdtos.createreturnorder.CreateReturnOrderDto;
import com.sapo.salemanagement.dto.orderdtos.ReturnOrderDetailInfo;
import com.sapo.salemanagement.dto.orderdtos.ReturnOrderItemDto;
import com.sapo.salemanagement.models.ReturnOrder;
import com.sapo.salemanagement.models.ReturnOrderLine;
import com.sapo.salemanagement.services.order.ReturnOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/return_orders")
@Validated
public class ReturnOrderController {

    @Autowired
    private ReturnOrderService returnOrderService;

    @GetMapping
    public ResponseEntity<PagedResponseObject> getReturnOrderList(@RequestParam(name = "page", defaultValue = "0") @Valid int page,
                                                            @RequestParam(name = "size", defaultValue = "10") @Valid int size,
                                                            @RequestParam(name = "search", defaultValue = "") String search) {
        long totalItems = returnOrderService.countTotalReturnOrders();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<ReturnOrderItemDto> orders = returnOrderService.getReturnOrderList(page, size, search);
        return ResponseEntity.ok(PagedResponseObject.builder()
                .page(page)
                .perPage(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(orders)
                .message("Success")
                .responseCode(200)
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getReturnOrderDetailInfo(@PathVariable int id) {
        ReturnOrderDetailInfo returnOrderDetailInfo = returnOrderService.getReturnOrderDetailInfo(id);
        return ResponseEntity.ok(ResponseObject.builder()
                        .data(returnOrderDetailInfo)
                        .message("success")
                        .responseCode(200)
                        .build());
    }

    @GetMapping("{id}/return_order_lines")
    public ResponseEntity<ResponseObject> getReturnOrderLines(@PathVariable int id) {
        List<ReturnOrderLine> returnOrderLines = returnOrderService.getAllReturnOrderLines(id);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(returnOrderLines)
                .message("success")
                .responseCode(200)
                .build());
    }

    @PostMapping("create")
    public ResponseEntity<ResponseObject> createReturnOrder(@RequestParam(name = "order_id") int orderId,
                                                            @RequestBody @Valid CreateReturnOrderDto createReturnOrderDto,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        String staffPhone = userDetails.getUsername();
        ReturnOrder returnOrder = returnOrderService.createReturnOrder(orderId, createReturnOrderDto, staffPhone);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .data(returnOrder.getId())
                .responseCode(200)
                .build());
    }
}
