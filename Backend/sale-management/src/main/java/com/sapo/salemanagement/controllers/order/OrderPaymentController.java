package com.sapo.salemanagement.controllers.order;

import com.sapo.salemanagement.dto.ResponseObject;
import com.sapo.salemanagement.dto.payment.MakePaymentDto;
import com.sapo.salemanagement.services.order.OrderPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/payment/orders")
@Validated
public class OrderPaymentController {

    @Autowired
    private OrderPaymentService paymentService;

    @PostMapping
    public ResponseEntity<ResponseObject> makePayment(@RequestBody @Valid MakePaymentDto makePaymentDto) {
        paymentService.makeOrderPayment(makePaymentDto);
        return ResponseEntity.ok(ResponseObject.builder()
                .message("success")
                .responseCode(200)
                .build());
    }
}
