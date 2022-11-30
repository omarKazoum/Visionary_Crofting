package com.visionary.crofting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.visionary.crofting.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;
    private String reference;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdAt = new Date();
    private double totalPrice=-1;
    private Long clientId;
    private Order.OrderStatusEnum status;
}
