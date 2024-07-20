package com.sumit.inventoryservice.controller;

import com.sumit.inventoryservice.dto.InventoryResponse;
import com.sumit.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //http://localhost:8002/api/inventory/iphone-12
    //http://localhost:8002/api/inventory?sku-code=iphone12&sku-code=iphone23
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
