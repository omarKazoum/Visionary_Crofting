package com.visionary.crofting.request;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.Product;
import lombok.Data;

import javax.persistence.*;
@Data
public class OrderItemDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private Long productId;
    private float totalPrice;
    private int quantity;
    private Long orderId;
}
