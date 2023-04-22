package com.inditex.logandotest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "size")
@Data
public class Size {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "back_soon", nullable = false)
    private boolean backSoon;

    @Column(nullable = false)
    private boolean special;

}
