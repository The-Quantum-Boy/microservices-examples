package com.sumit.javatechie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDER_TB")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int qty;

    private double price;
}
