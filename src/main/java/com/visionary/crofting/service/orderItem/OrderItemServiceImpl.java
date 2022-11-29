package com.visionary.crofting.service.orderItem;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.repository.OrderItemRepository;
import com.visionary.crofting.repository.OrderRepository;
import com.visionary.crofting.repository.ProductRepository;
import com.visionary.crofting.request.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@Transactional(rollbackOn =BusinessException.class )
public class OrderItemServiceImpl implements IOrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public OrderItem add(OrderItemDTO orderItemDTO) throws BusinessException {
        List<String> errors=new ArrayList<>();
        if(isOrderItemDTOValide(orderItemDTO,errors)){
            OrderItem orderItemToSave=new OrderItem();
            orderItemToSave.setOrder(orderRepository.findById(orderItemDTO.getOrderId()).get());
            orderItemToSave.setQuantity(orderItemDTO.getQuantity());
            orderItemToSave.setProduct(productRepository.findById(orderItemDTO.getProductId()).get());
            orderItemToSave.setTotalPrice(orderItemDTO.getTotalPrice());
            return orderItemRepository.save(orderItemToSave);
        }else{
            throw new BusinessException("invalide data",errors);
        }
    }

    @Override
    public OrderItem update(OrderItemDTO orderItemDTO) throws BusinessException {

        List<String> errors=new ArrayList<>();
        if(isOrderItemDTOValide(orderItemDTO,errors)){
            OrderItem orderItemToSave=orderItemRepository.findById(orderItemDTO.getId()).get();
            orderItemToSave.setOrder(orderRepository.findById(orderItemDTO.getOrderId()).get());
            orderItemToSave.setQuantity(orderItemDTO.getQuantity());
            orderItemToSave.setProduct(productRepository.findById(orderItemDTO.getProductId()).get());
            orderItemToSave.setTotalPrice(orderItemDTO.getTotalPrice());
            return orderItemRepository.save(orderItemToSave);
        }else{
            throw new BusinessException("invalide data",errors);
        }
    }

    @Override
    public void delete(Long id) throws BusinessException {

    }

    @Override
    public List<OrderItem> getAll()  {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOne(Long id) throws BusinessException {
        OrderItem oi;
        try{
            oi=orderItemRepository.findById(id).get();
        }catch (Exception e){

            throw new BusinessException("invalid token", Arrays.asList("can't find an orderItem with id "+id));
        }
        return oi;
    }


    boolean isOrderItemDTOValide(OrderItemDTO orderItem, List<String> errors){
        boolean valide=true;
        if(orderItem.getOrderId()==0 || !orderRepository.existsById(orderItem.getOrderId())) {
            errors.add("order item not associated with any order!");
            valide = false;
        }
        if(orderItem.getProductId()==0/*TODO continue this to validate if the id actually exists */) {
            errors.add("order item not associated with any product!");
            valide = false;
        }
        if(orderItem.getQuantity()<=0) {
            errors.add("invalid product quantity '"+orderItem.getQuantity()+"' not allowed!");
            valide = false;
        }

        return valide;
    }
}
