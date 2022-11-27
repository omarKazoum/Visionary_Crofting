package com.visionary.crofting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    LocalDate date;
    float totalPrice;
    //TODO add client many to one relationship here

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    List<OrderItem> items = new java.util.ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    OrderStatusEnum status;
    enum OrderStatusEnum{
        CREATED,
        CANCELED,
        PAYED,
        DELIVERED
    }

}
