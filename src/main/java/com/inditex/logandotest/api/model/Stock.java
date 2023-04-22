package com.inditex.logandotest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock {

    @Id
    private Long sizeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    private Size size;

    @Column(nullable = false)
    private Integer quantity;

}
