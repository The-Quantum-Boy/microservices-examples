package com.sumit.productservice.service;

import com.sumit.productservice.dto.ProductRequest;
import com.sumit.productservice.dto.ProductResponse;
import com.sumit.productservice.model.Product;
import com.sumit.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){

        String id= UUID.randomUUID().toString();

        Product product=Product.builder()
                .id(id)
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product {} is saved..",product.getId());
    }


    public List<ProductResponse> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product->mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
