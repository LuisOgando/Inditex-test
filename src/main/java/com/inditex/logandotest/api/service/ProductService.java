package com.inditex.logandotest.api.service;

import com.inditex.logandotest.api.model.Product;
import com.inditex.logandotest.api.repository.ProductRepository;
import com.inditex.logandotest.api.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

public interface ProductService {
    List<Product> getAll();
}

@Service
@AllArgsConstructor
class ProductServiceImpl implements ProductService {

    private GetOnStockProduct getOnStockProduct;
    private ProductRepository repository;
    private SizeRepository sizeRepository;

    @Override
    public List<Product> getAll() {
        var sizeList = sizeRepository.findAll();
        var onStockProductIds = Collections.EMPTY_SET;
        if (!sizeList.isEmpty()) {
            onStockProductIds = getOnStockProduct.apply(sizeList);
        }
        return repository.findAllByIdInOrderBySequence(onStockProductIds);
    }
}