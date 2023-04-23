package com.inditex.logandotest.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {

    @JsonProperty("product_id")
    private Long id;

    @JsonProperty("sequence")
    private Long sequence;

}
