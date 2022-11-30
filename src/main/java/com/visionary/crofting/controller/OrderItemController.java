package com.visionary.crofting.controller;

import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.requests.OrderItemDTO;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IOrderItemService;
import com.visionary.crofting.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    @Autowired
    IOrderItemService orderItemService;
    @PostMapping("")
    ResponseEntity add(@RequestBody OrderItemDTO orderItemDTO){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            OrderItem orderItem = orderItemService.add(orderItemDTO);
            response.setData(EntityUtils.orderItemDTOToOrderItem(orderItem));
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(BusinessException businessException){
            response.setResponseMessage(businessException.getMessage());
            response.setErrors(businessException.getErrors());
            response.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("")
    ResponseEntity update(@RequestBody  OrderItemDTO orderItemDto){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            response.setData(EntityUtils.orderItemDTOToOrderItem(orderItemService.update(orderItemDto)));
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (BusinessException exception){
            response.setResponseMessage(exception.getMessage());
            response.setErrors(exception.getErrors());
            response.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
    };

    @GetMapping("/{orderItemId}")
    ResponseEntity getSingle( @PathVariable("orderItemId") Long orderItemId){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            response.setData(EntityUtils.orderItemDTOToOrderItem(orderItemService.getOne(orderItemId)));
            response.setResponseMessage("successfully retrieved order item!");
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(BusinessException exception){
            response.setResponseMessage(exception.getMessage());
            exception.setErrors(exception.getErrors());
            response.setResponseMessage("unable to satisfy your request!");
            response.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    ResponseEntity getAll(@RequestHeader Map<String,String> requestHeaders){
        System.out.println("request headers");
        requestHeaders.keySet().stream().forEach(System.out::println);
        System.out.println("request header end");
        //TODO add pagination
        ApiResponse<List<OrderItemDTO>> response=new ApiResponse<>();
        response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        response.setResponseMessage("successfully got order items");
        response.setData(orderItemService.getAll().stream().map(EntityUtils::orderItemDTOToOrderItem).collect(Collectors.toList()));
        return new ResponseEntity(response,HttpStatus.OK);
    };
    @DeleteMapping("/{orderItemId}")
    ResponseEntity deleteOrderItem(@PathVariable("orderItemId") Long orderItemId){
        ApiResponse<OrderItemDTO> response=new ApiResponse<>();
        try {
            //response.setData(EntityUtils.orderItemDTOToOrderItem());
            orderItemService.delete(orderItemId);
            response.setResponseMessage(String.format("successfully deleted order item with id %d !",orderItemId));
            response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch(BusinessException exception){
            response.setResponseMessage(exception.getMessage());
            exception.setErrors(exception.getErrors());
            response.setResponseMessage("unable to satisfy your request!");
            response.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }

    }

}
