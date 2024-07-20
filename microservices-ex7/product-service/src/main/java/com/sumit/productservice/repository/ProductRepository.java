package com.sumit.productservice.repository;

import com.sumit.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,String> {
}
