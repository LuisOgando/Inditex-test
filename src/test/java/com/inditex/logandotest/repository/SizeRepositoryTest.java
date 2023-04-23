package com.inditex.logandotest.repository;

import com.inditex.logandotest.api.repository.SizeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Size Repository Test")
public class SizeRepositoryTest {

    @Autowired
    SizeRepository repository;

    @Nested
    @DisplayName("findAll() Tests")
    class findAllTest {
        @Test
        void find_all_by_id_in_order_by_sequence_should_return_a_size_list() {

            var productList = repository.findAll();
            assertThat(productList).isNotNull();
            assertThat(productList.get(0)).isNotNull();
            assertThat(productList.isEmpty()).isFalse();
            assertThat(productList.size()).isGreaterThan(0);

        }
    }

}
