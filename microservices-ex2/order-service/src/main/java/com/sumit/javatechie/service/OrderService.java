package com.sumit.javatechie.service;

import com.sumit.javatechie.common.Payment;
import com.sumit.javatechie.common.TransactionRequest;
import com.sumit.javatechie.common.TransactionResponse;
import com.sumit.javatechie.entity.Order;
import com.sumit.javatechie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request){

        System.out.println(ENDPOINT_URL);

        String response="";

        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        System.out.println("OrderService.saveOrder request {} "+ request);
        //rest call

//        Payment res = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        Payment res = restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);


        response = res.getPaymentStatus().equals("SUCCESS") ? "payment processing successful and order placed " : "there is failure in payement api while order added to cart";
        System.out.println("OrderService.saveOrder response {} "+response);


        repository.save(order);

        return new TransactionResponse(order,res.getAmount(),res.getTransactionId(),response);
    }

    public Order findByPaymentHistoryByOrderId(int orderId) {
        return repository.findById(orderId).get();
    }
}
