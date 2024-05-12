package com.sapo.salemanagement.controllers.order;

import com.sapo.salemanagement.dto.PagedResponseObject;
import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.orderdtos.*;
import com.sapo.salemanagement.dto.orderdtos.createorder.CreateOrderDto;
import com.sapo.salemanagement.models.OrderLine;
import com.sapo.salemanagement.services.order.OrderService;
import com.sapo.salemanagement.services.order.ReturnOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReturnOrderService returnOrderService;

    @GetMapping
    public ResponseEntity<PagedResponseObject> getOrderList(@RequestParam(value = "page", defaultValue = "0") @Valid int page,
                                                            @RequestParam(value = "size", defaultValue = "10") @Valid int size,
                                                            @RequestParam(defaultValue = "") String search) {
        long totalItems = orderService.countTotalOrders();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        List<OrderListItemDto> orders = orderService.getOrderList(page, size, search);
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

    @PostMapping
    public ResponseEntity<ResponseObject> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto, @AuthenticationPrincipal UserDetails userDetails) {
        String staffPhone = userDetails.getUsername();
        orderService.createOrder(createOrderDto, staffPhone);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .responseCode(200)
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getOrderDetailInfo(@PathVariable @Valid int id) {
        OrderDetailInfo orderDetailInfo = orderService.getOrderDetailInfo(id);
        return ResponseEntity.ok(ResponseObject.builder()
                        .data(orderDetailInfo)
                        .message("success")
                        .responseCode(200)
                        .build());
    }

    @GetMapping("{id}/order_lines")
    public ResponseEntity<ResponseObject> getAllOrderLines(@PathVariable @Valid int id) {
        List<OrderLine> orderLines = orderService.getAllOrderLines(id);
        return ResponseEntity.ok(ResponseObject.builder()
                        .responseCode(200)
                        .message("success")
                        .data(orderLines)
                        .build());
    }

    @GetMapping("{id}/return_histories")
    public ResponseEntity<ResponseObject> getReturnHistories(@PathVariable @Valid int id) {
        List<ReturnHistoryItemDto> historyItemDtoList = returnOrderService.getReturnHistories(id);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("success")
                .data(historyItemDtoList)
                .build());
    }

    @GetMapping("/statistical")
    public ResponseEntity<ResponseObject> statisticalByTime(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @AuthenticationPrincipal UserDetails userDetails) {
//        String staffPhone = userDetails.getUsername();
        List<OrderLine> list = orderService.statisticalByTime(startDate, endDate);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("success")
                .data(list)
                .build());
    }

    @GetMapping("/statistical/list")
    public ResponseEntity<ResponseObject> statisticalListByTime(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        List<OrderStatistical> list = orderService.statisticalListByTime(startDate, endDate);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("success")
                .data(list)
                .build());
    }

    @GetMapping("/top_sale")
    public ResponseEntity<ResponseObject> topOrder(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("type") String type, @AuthenticationPrincipal UserDetails userDetails) {
//        String staffPhone = userDetails.getUsername();
        List<TopOrder> list = orderService.topOrder(startDate, endDate, type);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("success")
                .data(list)
                .build());
    }

    @GetMapping("/top_customer")
    public ResponseEntity<ResponseObject> topCustomer(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("type") String type, @AuthenticationPrincipal UserDetails userDetails) {
//        String staffPhone = userDetails.getUsername();
        List<TopCustomer> list = orderService.topCustomer(startDate, endDate, type);
        return ResponseEntity.ok(ResponseObject.builder()
                .responseCode(200)
                .message("success")
                .data(list)
                .build());
    }
}
