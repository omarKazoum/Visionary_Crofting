package com.visionary.crofting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "reference",length = 20,nullable = false,unique = true)
    private String reference;
    @Column(name = "title",nullable = false,unique = true)
    private String title;
    @Column(name = "description" , nullable = false)
    private String description;
    @Column(name = "initial_price" , nullable = false)
    private float initialPrice ;
    @Column(name = "quantity" , nullable = false)
    private int quantity;
    @ManyToOne
    private Stock stock;
}
