package com.visionary.crofting.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.persistence.EnumType;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "productRequest")
public class ProductRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reference_product",nullable = false)
    private String productReference;

    @Column(nullable = false)
    private Integer quantity;
    @ManyToOne
    private Stock stock;
    @Enumerated(EnumType.STRING)
    private SupplyRequestEnum status = SupplyRequestEnum.OPEN;
    private LocalDate createdAt = LocalDate.now();
    @ManyToOne
    private Supplier supplier;


    public enum SupplyRequestEnum{
        OPEN,
        CANCELED,
        CONFIRMED,
        CLOSED
    }

    public ProductRequest(String productReference, Integer quantity, Stock stock) {
        this.productReference = productReference;
        this.quantity = quantity;
        this.stock = stock;
    }

}
