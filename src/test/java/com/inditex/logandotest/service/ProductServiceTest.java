package com.inditex.logandotest.service;

import com.inditex.logandotest.api.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Product Service Test")
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Nested
    @DisplayName("findAll() Tests")
    class findAllTest {
        @Test
        void find_all_should_return_a_not_empty_product_list() {
            final var productList = service.findAll();
            assertThat(productList).isNotNull();
            assertThat(productList.get(0)).isNotNull();
            assertThat(productList.isEmpty()).isFalse();
            assertThat(productList.size()).isGreaterThan(0);
        }
        @Test
        void find_all_should_return_a_ordered_product_list() {
            final var productList = service.findAll();
            assertThat(productList).isNotNull();
            assertThat(productList.get(0)).isNotNull();
            assertThat(productList.get(1)).isNotNull();
            assertThat(productList.isEmpty()).isFalse();
            assertThat(productList.size()).isGreaterThan(0);

            var product1 = productList.get(0);
            var product2 = productList.get(1);

            assertThat(product1.getSequence()).isLessThan(product2.getSequence());
        }
    }
}
