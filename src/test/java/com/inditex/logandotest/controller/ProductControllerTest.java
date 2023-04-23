package com.inditex.logandotest.controller;

import com.inditex.logandotest.api.controller.ProductController;
import com.inditex.logandotest.api.model.Product;
import com.inditex.logandotest.api.service.ProductService;
import org.assertj.core.util.Lists;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product Controller Test")
public class ProductControllerTest {

    @Mock
    private ProductService mockService;
    private String findAllUri;
    private MockMvc mockMvc;
    private EasyRandom randomGeneration;


    @BeforeEach
    public void setUp() {
        randomGeneration = new EasyRandom();

        final var requestMappingAnnotation = ProductController.class.
                getDeclaredAnnotation(RequestMapping.class);

        final var baseUri = requestMappingAnnotation.value()[0];
        final var controller = new ProductController(mockService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        findAllUri = baseUri;

    }

    @Nested
    @DisplayName("findAll() Tests")
    class FindAllTests {

        @Test
        void find_all_returns_http_status_200_on_agent_service_returns_empty_list() throws Throwable {
            when(mockService.findAll())
                    .thenReturn(Collections.emptyList());

            mockMvc
                    .perform(get(findAllUri))
                    .andExpect(status().isOk());
        }

        @Test
        void find_all_returns_http_status_200() throws Throwable {
            final var fakeProductList =
                    Lists.newArrayList(
                            randomGeneration.nextObject(Product.class),
                            randomGeneration.nextObject(Product.class),
                            randomGeneration.nextObject(Product.class)
                    );

            when(mockService.findAll())
                    .thenReturn(fakeProductList);

            mockMvc
                    .perform(get(findAllUri))
                    .andExpect(status().isOk());
        }
    }
}
