package com.visionary.crofting.dto;

import com.visionary.crofting.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Date date;
    private String reference;
    private Date createdAt = new Date();
    private double totalPrice;
    private Long clientId;
    private Order.OrderStatusEnum status;

}
