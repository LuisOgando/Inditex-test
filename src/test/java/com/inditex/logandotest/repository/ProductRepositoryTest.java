package com.inditex.logandotest.repository;

import com.inditex.logandotest.api.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Product Repository Test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Nested
    @DisplayName("findAllByIdInOrderBySequence() Tests")
    class findAllByIdInOrderBySequenceTest {

        @Test
        void find_all_by_id_in_order_by_sequence_should_return_a_product_list() {
            final var fakeProductIds = Set.of(1L, 2L, 3L);

            var productList = repository.findAllByIdInOrderBySequence(fakeProductIds);
            assertThat(productList).isNotNull();
            assertThat(productList.get(0)).isNotNull();
            assertThat(productList.isEmpty()).isFalse();
            assertThat(productList.size()).isGreaterThan(0);

        }

        @Test
        void find_all_by_id_in_order_by_sequence_should_return_a_ordered_product_list() {
            final var fakeProductIds = Set.of(1L, 2L, 3L);

            var productList = repository.findAllByIdInOrderBySequence(fakeProductIds);
            assertThat(productList).isNotNull();
            assertThat(productList.get(0)).isNotNull();
            assertThat(productList.get(1)).isNotNull();
            assertThat(productList.isEmpty()).isFalse();
            assertThat(productList.size()).isGreaterThan(0);

            var product1 = productList.get(0);
            var product2 = productList.get(1);

            assertThat(product1.getSequence()).isLessThan(product2.getSequence());

        }

        @Test
        void find_all_by_id_in_order_by_sequence_should_return_an_empty_list() {
            final var fakeProductIds = Collections.EMPTY_SET;

            var productList = repository.findAllByIdInOrderBySequence(fakeProductIds);
            assertThat(productList).isNotNull();
            assertThat(productList.isEmpty()).isTrue();
            assertThat(productList.size()).isEqualTo(0);

        }
    }
}
