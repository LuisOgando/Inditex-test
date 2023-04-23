package com.inditex.logandotest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock {

    @Id
    private Long sizeId;

    @Column(nullable = false)
    private Integer quantity;

}
