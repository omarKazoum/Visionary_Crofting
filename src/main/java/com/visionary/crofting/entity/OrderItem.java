package com.visionary.crofting.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    //TODO add product manyToOneRelationship

    float totalPrice;
    int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



}