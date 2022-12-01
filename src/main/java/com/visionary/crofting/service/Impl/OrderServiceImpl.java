package com.visionary.crofting.service.Impl;

import com.visionary.crofting.dto.OrderDTO;
import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.repository.ClientRepository;
import com.visionary.crofting.repository.OrderRepository;
import com.visionary.crofting.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Order addOrder(OrderDTO orderDTO) throws BusinessException{
        List<String> errors=new ArrayList<>();
        if(!isOrderDTOValide(orderDTO,errors,OperationENum.ADD))
            throw new BusinessException("invalid token",errors);
            ModelMapper modelMapper=new ModelMapper();
            Order orderToSave=modelMapper.map(orderDTO,Order.class);
            orderToSave.setClient(clientRepository.findById(orderDTO.getClientId()).get());
            orderToSave.setReference(UUID.randomUUID().toString());
            orderToSave.setTotalPrice(0);
            orderToSave.setCreatedAt(new Date());
            orderToSave.setStatus(Order.OrderStatusEnum.CREATED);
            orderToSave.setId(null);
            orderToSave=orderRepository.save(orderToSave);
        return orderToSave;
    }

    @Override
    public Order confirmOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrder(OrderDTO orderDTO) throws BusinessException{
        List<String> errors=new ArrayList<>();
        if(!isOrderDTOValide(orderDTO,errors,OperationENum.ADD))
            throw new BusinessException("invalid token",errors);

        Order orderToUpdate=orderRepository.findById(orderDTO.getId()).get();
        orderToUpdate.setClient(clientRepository.findById(orderDTO.getClientId()).get());
        orderToUpdate.setTotalPrice(orderDTO.getTotalPrice()!=-1?orderDTO.getTotalPrice():orderToUpdate.getTotalPrice());
        orderToUpdate.setCreatedAt(orderDTO.getCreatedAt()!=null?orderDTO.getCreatedAt():orderToUpdate.getCreatedAt());
        orderToUpdate.setDate(orderDTO.getDate()!=null?orderDTO.getDate():orderToUpdate.getDate());
        orderToUpdate.setStatus(Order.OrderStatusEnum.CREATED);
        orderToUpdate=orderRepository.save(orderToUpdate);

        return orderToUpdate;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderItem> getOrderItemsPerOrder(Long orderId) throws BusinessException{
        if(!orderRepository.existsById(orderId))
            throw new BusinessException("invalide order id", Arrays.asList("Unknown order id "));
        return orderRepository.findById(orderId).get().getOrderItems();
    }

    boolean isOrderDTOValide(OrderDTO orderDTO, List<String> errors,OperationENum operation){
        boolean valide=true;
        if(operation.equals(OperationENum.UPDATE)){

        }
        if(operation.equals(OperationENum.UPDATE) && (orderDTO.getClientId()==null || !clientRepository.existsById(orderDTO.getId()))) {
            errors.add("invalid client id!");
            valide = false;
        }
        if(operation.equals(OperationENum.UPDATE) && (orderDTO.getId()==null || !orderRepository.existsById(orderDTO.getId()))) {
            errors.add("invalid order id!");
            valide = false;
        }

       //TODO continue validation

        return valide;
    }
    enum OperationENum{
        ADD,
        UPDATE

    }

}
