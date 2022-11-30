package com.visionary.crofting.requests;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.Product;
import lombok.Data;

import javax.persistence.*;
@Data
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private float totalPrice;
    private int quantity;
    private Long orderId;
}
