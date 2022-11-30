package com.visionary.crofting.service.Impl;

import com.visionary.crofting.dto.OrderItemDTO;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.repository.OrderItemRepository;
import com.visionary.crofting.repository.OrderRepository;
import com.visionary.crofting.repository.ProductRepository;
import com.visionary.crofting.service.IOrderItemService;
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
        if(!orderItemRepository.existsById(id)){
            throw new BusinessException("order item not found",Arrays.asList("invalid item id"));
        }else
            orderItemRepository.deleteById(id);

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


    boolean isOrderItemDTOValide(OrderItemDTO orderItemDTO, List<String> errors){
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
}
