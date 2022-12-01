package com.visionary.crofting.dto;

import lombok.Data;

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
