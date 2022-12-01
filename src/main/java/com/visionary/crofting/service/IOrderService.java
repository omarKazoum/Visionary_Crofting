package com.visionary.crofting.service;

import com.visionary.crofting.dto.OrderDTO;
import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;

import java.util.List;

public interface IOrderService {
    Order addOrder(OrderDTO order) throws BusinessException;
    Order confirmOrder(Order order) throws BusinessException;
    Order updateOrder(OrderDTO order) throws BusinessException;
    List<Order> getAll();
    List<OrderItem> getOrderItemsPerOrder(Long orderId) throws BusinessException;
}
