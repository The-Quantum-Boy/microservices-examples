package com.sumit.payment.service;

import com.sumit.payment.entity.Payment;
import com.sumit.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());

        System.out.println("PaymentService.doPayment request {} "+payment);
        return repository.save(payment);
    }

    public String paymentProcessing(){
        //api should be third party payment gateway
        return new Random().nextBoolean()?"SUCCESS":"false";
    }


    public Payment findByPaymentHistoryByOrderId(int orderId) {
        System.out.println("PaymentService.findByPaymentHistoryByOrderId {} "+repository.findByOrderId(orderId));
        return  repository.findByOrderId(orderId);
    }
}
