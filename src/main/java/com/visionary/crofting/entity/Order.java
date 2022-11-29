package com.visionary.crofting.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date date;
    @Column(name = "reference",nullable = false,unique = true)
    private String reference;
    private Date createdAt = new Date();
    @NotNull
    @Column(name = "total_price",nullable = false)
    private double totalPrice;
    @ManyToOne
    private Client client;
    @OneToMany
    private List<OrderItem> orderItems;
    private OrderStatusEnum status;
    public enum OrderStatusEnum{
        CREATED,
        CANCELED,
        PAYED,
        DELIVERED
    }


}
