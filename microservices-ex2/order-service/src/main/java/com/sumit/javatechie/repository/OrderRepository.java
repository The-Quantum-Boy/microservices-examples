package com.sumit.javatechie.repository;

import com.sumit.javatechie.common.Payment;
import com.sumit.javatechie.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
