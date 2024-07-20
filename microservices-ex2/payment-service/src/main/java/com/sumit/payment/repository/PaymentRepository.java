package com.sumit.payment.repository;

import com.sumit.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment , Integer> {

    Payment findByOrderId(int orderId);

}
