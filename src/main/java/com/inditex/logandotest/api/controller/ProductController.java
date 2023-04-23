package com.inditex.logandotest.api.controller;

import com.inditex.logandotest.api.model.Product;
import com.inditex.logandotest.api.repository.ProductRepository;
import com.inditex.logandotest.api.service.GetProductSizes;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository repository;
    private final GetProductSizes getProductSizes;

    @GetMapping()
    private ResponseEntity<String> getAll(
    ) {
        List<Product> productList = repository.findAll();
        var sizes =  getProductSizes.apply();
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
