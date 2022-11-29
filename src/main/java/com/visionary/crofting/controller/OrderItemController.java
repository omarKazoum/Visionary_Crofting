package com.visionary.crofting.controller;

import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.request.OrderItemDTO;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.orderItem.IOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceUnit;
import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    @Autowired
    IOrderItemService orderItemService;
    @PostMapping("")
    ApiResponse<OrderItemDTO> add(@RequestBody OrderItemDTO orderItemDTO){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            OrderItem orderItem = orderItemService.add(orderItemDTO);
            response.setData(orderItemDTOToOrderItem(orderItem));
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        }catch(BusinessException businessException){
            response.setResponseMessage(businessException.getMessage());
            response.setErrors(businessException.getErrors());
            response.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
        }
        return response;

    }
    @PutMapping("")
    ApiResponse<OrderItemDTO> update(@RequestBody  OrderItemDTO orderItemDto){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            response.setData(orderItemDTOToOrderItem(orderItemService.update(orderItemDto)));
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        }catch (BusinessException exception){
            response.setResponseMessage(exception.getMessage());
            response.setErrors(exception.getErrors());
            response.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
        }
        return response;
    };

    @GetMapping("/{orderItemId}")
    ApiResponse<OrderItemDTO> getSingle( @PathVariable("orderItemId") Long orderItemId){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            response.setData(orderItemDTOToOrderItem(orderItemService.getOne(orderItemId)));
            response.setResponseMessage("successfully retrieved order item!");
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);

        }catch(BusinessException exception){
            response.setResponseMessage(exception.getMessage());
            exception.setErrors(exception.getErrors());
            response.setResponseMessage("unable to satisfy your request!");
            response.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
        }
        return response;
    }
    /*
    void delete(OrderItem orderItem);
    OrderItem getOne(Long id);*/
    @GetMapping("")
    ApiResponse<List<OrderItemDTO>> getAll(){
        //TODO finish this
        ApiResponse<List<OrderItemDTO>> response=new ApiResponse<>();
        response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        response.setResponseMessage("successfully got order items");
        response.setData(orderItemService.getAll().stream().map(this::orderItemDTOToOrderItem).collect(Collectors.toList()));
        return response;
    };

    @GetMapping("/test")
    OrderItemDTO test(){
        return new OrderItemDTO();
    }
    OrderItemDTO orderItemDTOToOrderItem(OrderItem orderItem){
        ModelMapper modelMapper =new ModelMapper();
        OrderItemDTO oidto=modelMapper.map(orderItem,OrderItemDTO.class);
        return oidto;
    }
}
