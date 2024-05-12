package com.sapo.salemanagement.services.vendor;

import com.sapo.salemanagement.dto.vendorDtos.PaymentDTO;

import java.util.List;

public interface IPaymentService {
    PaymentDTO save (PaymentDTO paymentDTO);
    PaymentDTO findById_ImportOrderId(Integer id);

    List<PaymentDTO> findAll();
}
