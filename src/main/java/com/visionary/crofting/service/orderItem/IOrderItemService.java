package com.visionary.crofting.service.orderItem;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.request.OrderItemDTO;

import javax.transaction.Transactional;
import java.util.List;
public interface IOrderItemService {
    OrderItem add(OrderItemDTO orderItem) throws BusinessException;
    OrderItem update(OrderItemDTO orderItem) throws BusinessException;
    void delete(Long id) throws BusinessException;
    List<OrderItem> getAll();
    OrderItem getOne(Long id) throws BusinessException;
}
