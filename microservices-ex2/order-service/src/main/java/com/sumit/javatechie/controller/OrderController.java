package com.sumit.javatechie.controller;

import com.sumit.javatechie.common.TransactionRequest;
import com.sumit.javatechie.common.TransactionResponse;
import com.sumit.javatechie.entity.Order;
import com.sumit.javatechie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
        return orderService.saveOrder(request);
    }

    @GetMapping("/{orderId}")
    public Order findPaymentById(@PathVariable int orderId){
        return orderService.findByPaymentHistoryByOrderId(orderId);
    }
}
