package com.inditex.logandotest.api.controller;

import com.inditex.logandotest.api.controller.dto.ProductDto;
import com.inditex.logandotest.api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    List<ProductDto> map(List<Product> products);
}
