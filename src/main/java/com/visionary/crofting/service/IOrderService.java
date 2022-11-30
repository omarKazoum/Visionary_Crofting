package com.visionary.crofting.service;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;

import java.util.List;

public interface IOrderService {
    Order addOrder(Order order);
    Order confirmOrder(Order order);
    Order updateOrder(Order order);
    List<OrderItem> getOrderItemsPerOrder(Long orderId) throws BusinessException;
}
