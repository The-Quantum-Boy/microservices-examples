package com.sumit.payment.controller;

import com.sumit.payment.entity.Payment;
import com.sumit.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return service.doPayment(payment);
    }


    @GetMapping("/find/{orderId}")
    public Payment findPaymentById(@PathVariable int orderId){
        return service.findByPaymentHistoryByOrderId(orderId);
    }


}
