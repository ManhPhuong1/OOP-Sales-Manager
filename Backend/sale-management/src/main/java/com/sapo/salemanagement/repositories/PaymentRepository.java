package com.sapo.salemanagement.repositories;

import com.sapo.salemanagement.models.Order;
import com.sapo.salemanagement.models.Payment;
import com.sapo.salemanagement.models.enums.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findPaymentByOrderId(int orderId);
    Payment findPaymentByOrderIdAndOrderType(int orderId, OrderType orderType);

    @Query("SELECT p FROM Payment p WHERE p.orderId = :orderId AND p.orderType = 'ORDER' ")
    Payment findPaymentByOrder(@Param("orderId") int orderId);
} 
