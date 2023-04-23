package com.inditex.logandotest.service;

import com.inditex.logandotest.api.model.Product;
import com.inditex.logandotest.api.model.Size;
import com.inditex.logandotest.api.model.Stock;
import com.inditex.logandotest.api.service.GetProductInStock;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@DisplayName("Get In stock Product Test")
public class GetProductInStockTest {

    @Autowired
    private GetProductInStock getProductInStock;
    private EasyRandom randomGeneration;

    @BeforeEach
    public void setUp() {
        randomGeneration = new EasyRandom();
    }


    @Nested
    @DisplayName("apply() Tests")
    class applyTest {
        @Test
        void test_apply_empty_list() {
            final var sizeList = new ArrayList<Size>();
            final var result = getProductInStock.apply(sizeList);
            assertThat(result.size()).isEqualTo(0);
        }

        @Test
        void test_apply_all_sizes_out_of_stock() {
            var size1 = randomGeneration.nextObject(Size.class);
            var size2 = randomGeneration.nextObject(Size.class);
            var size3 = randomGeneration.nextObject(Size.class);
            size1.setStock(null);
            size1.setBackSoon(false);

            size2.setStock(null);
            size2.setBackSoon(false);

            size3.setStock(null);
            size3.setBackSoon(false);

            var sizeList = Arrays.asList(
                    size1, size2, size3
            );
            Set<Long> result = getProductInStock.apply(sizeList);
            assertThat(result.size()).isEqualTo(0);
        }

        @Test
        public void test_apply_all_sizes_backSoon() {
            var product1 = randomGeneration.nextObject(Product.class);
            var product2 = randomGeneration.nextObject(Product.class);
            var size1 = randomGeneration.nextObject(Size.class);
            var size2 = randomGeneration.nextObject(Size.class);
            var size3 = randomGeneration.nextObject(Size.class);

            size1.setProduct(product1);
            size1.setBackSoon(true);
            size1.setSpecial(false);

            size2.setProduct(product1);
            size2.setBackSoon(true);
            size2.setSpecial(false);

            size3.setProduct(product2);
            size3.setBackSoon(true);
            size3.setSpecial(false);

            var sizeList = Arrays.asList(
                    size1, size2, size3
            );
            Set<Long> result = getProductInStock.apply(sizeList);
            assertThat(result.size()).isEqualTo(2);
            assertThat(result.contains(product1.getId()));
            assertThat(result.contains(product2.getId()));

        }

        @Test
        public void test_apply_some_sizes_back_aoon_and_some_in_stock() {
            var product1 = randomGeneration.nextObject(Product.class);
            var product2 = randomGeneration.nextObject(Product.class);
            var size1 = randomGeneration.nextObject(Size.class);
            var size2 = randomGeneration.nextObject(Size.class);
            var size3 = randomGeneration.nextObject(Size.class);
            var size4 = randomGeneration.nextObject(Size.class);

            size1.setProduct(product1);
            size1.setBackSoon(true);
            size1.setSpecial(false);
            size1.setStock(null);

            size2.setProduct(product1);
            size2.setBackSoon(false);
            size2.setSpecial(false);
            size2.setStock(null);

            size3.setProduct(product2);
            size3.setBackSoon(false);
            size3.setSpecial(false);
            size3.setStock(null);

            size4.setProduct(product2);
            size4.setBackSoon(false);
            size4.setSpecial(false);
            size4.setStock(null);


            var sizeList = Arrays.asList(
                    size1, size2, size3, size4
            );
            Set<Long> result = getProductInStock.apply(sizeList);
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.contains(product1.getId()));

        }

        @Test
        void test_apply_some_sizes_special_and_some_not() {
            var product1 = randomGeneration.nextObject(Product.class);
            var product2 = randomGeneration.nextObject(Product.class);
            var size1 = randomGeneration.nextObject(Size.class);
            var size2 = randomGeneration.nextObject(Size.class);
            var size3 = randomGeneration.nextObject(Size.class);
            var size4 = randomGeneration.nextObject(Size.class);

            size1.setProduct(product1);
            size1.setBackSoon(true);
            size1.setSpecial(false);
            size1.setStock(null);

            size2.setProduct(product1);
            size2.setBackSoon(false);
            size2.setSpecial(false);
            size2.setStock(null);

            size3.setProduct(product2);
            size3.setBackSoon(false);
            size3.setSpecial(false);
            size3.setStock(null);

            size4.setProduct(product2);
            size4.setBackSoon(false);
            size4.setSpecial(false);
            var stock2 = randomGeneration.nextObject(Stock.class);
            stock2.setQuantity(5);
            size4.setStock(stock2);

            List<Size> sizeList = Arrays.asList(
                    size1, size2, size3, size4
            );
            Set<Long> result =getProductInStock.apply(sizeList);
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.contains(product1.getId()));
        }
    }
}
