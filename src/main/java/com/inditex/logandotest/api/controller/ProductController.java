package com.inditex.logandotest.api.controller;

import com.inditex.logandotest.api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping()
    private ResponseEntity getAll() {
        var products = service.getAll();
        if (products.isEmpty()){
            return ResponseEntity.ok(Collections.EMPTY_LIST);
        } else {
            return ResponseEntity.ok(ProductMapper.INSTANCE.map(products));
        }
    }

}
