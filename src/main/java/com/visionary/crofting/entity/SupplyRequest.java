package com.visionary.crofting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "reference_product",nullable = false)
    private String productReference;
    @ManyToOne
    private Stock stock;
    private SupplyRequestEnum status;
    private Date createdAt = new Date();
    @ManyToOne
    private Supplier supplier;


    public enum SupplyRequestEnum{
        OPEN,
        CANCELED,
        CONFIRMED,
        CLOSED
    }

}
