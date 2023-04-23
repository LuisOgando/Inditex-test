package com.inditex.logandotest.controller;

import com.inditex.logandotest.api.controller.ProductMapper;
import com.inditex.logandotest.api.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Price Mapper Mapper")
public class ProductMapperTest {

    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = ProductMapper.INSTANCE;
    }

    @Test
    void model_price_mapped_to_product_response_dto() {
        final var model = new Product();
        model.setId(1L);
        model.setSequence(10);
        List<Product> productList = List.of(model);
        final var dtos = mapper.map(productList);

        assertThat(dtos)
                .isNotNull();

        assertThat(dtos)
                .isNotEmpty();

        assertThat(productList.get(0).getId())
                .isEqualTo(dtos.get(0).getId());

        assertThat(productList.get(0).getSequence())
                .isEqualTo(dtos.get(0).getSequence());
    }

}
