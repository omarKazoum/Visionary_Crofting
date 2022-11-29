package com.visionary.crofting.service.order;

import com.visionary.crofting.entity.Order;

public interface IOrderService {
    Order addOrder(Order order);
    Order confirmOrder(Order order);
    Order updateOrder(Order order);
}
