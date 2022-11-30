package com.visionary.crofting.service.Impl;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.repository.OrderRepository;
import com.visionary.crofting.requests.OrderItemDTO;
import com.visionary.crofting.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order addOrder(Order order) {
        //TODO implement this
        return null;
    }

    @Override
    public Order confirmOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsPerOrder(Long orderId) throws BusinessException{
        if(!orderRepository.existsById(orderId))
            throw new BusinessException("invalide order id", Arrays.asList("Unknown order id "));
        return orderRepository.findById(orderId).get().getOrderItems();
    }

    /*boolean isOrderItemDTOValide(OrderItemDTO orderItemDTO, List<String> errors){
        boolean valide=true;
        if(orderItemDTO.getOrderId()==0 || !orderRepository.existsById(orderItemDTO.getOrderId())) {
            errors.add("order item not associated with any order!");
            valide = false;
        }
        if(orderItemDTO.getProductId()==0 || !productRepository.existsById(orderItemDTO.getProductId())) {
            errors.add("order item not associated with any product!");
            valide = false;
        }
        if(orderItemDTO.getQuantity()<=0) {
            errors.add("invalid product quantity '"+orderItemDTO.getQuantity()+"' not allowed!");
            valide = false;
        }
        return valide;
    }
*/
}
