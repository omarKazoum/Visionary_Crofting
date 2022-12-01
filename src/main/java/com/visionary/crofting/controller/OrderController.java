package com.visionary.crofting.controller;

import com.visionary.crofting.dto.OrderDTO;
import com.visionary.crofting.dto.OrderItemDTO;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IOrderService;
import com.visionary.crofting.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @GetMapping("/{orderId}/orderItems")
    ResponseEntity getOrderItemsPerOrder(@PathVariable("orderId") Long orderId){
        ResponseEntity responseEntity;
            ApiResponse<List<OrderItemDTO>> apiResponse=new ApiResponse<>();
            try {
                apiResponse.setData(orderService.getOrderItemsPerOrder(orderId).stream().map(EntityUtils::orderItemDTOToOrderItem).collect(Collectors.toList()));
                apiResponse.setResponseMessage(String.format("successfully order items for order id %d !",orderId));
                apiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                responseEntity=new ResponseEntity(apiResponse, HttpStatus.OK);
            }catch(BusinessException exception){
                apiResponse.setResponseMessage(exception.getMessage());
                exception.setErrors(exception.getErrors());
                apiResponse.setResponseMessage("unable to satisfy your request!");
                apiResponse.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
                responseEntity=new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
    }
    @PostMapping("/")
    ResponseEntity addOrder(@RequestBody OrderDTO orderDTO){
        //continue this the service is currently returning null for testing purposes
        ResponseEntity responseEntity;
            ApiResponse<OrderDTO> apiResponse=new ApiResponse<>();
            try {
                apiResponse.setData(EntityUtils.orderToOrderDTO(orderService.addOrder(orderDTO)));
                apiResponse.setResponseMessage("successfully created your order !");
                apiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                responseEntity=new ResponseEntity(apiResponse, HttpStatus.OK);
            }catch(BusinessException exception){
                apiResponse.setResponseMessage(exception.getMessage());
                exception.setErrors(exception.getErrors());
                apiResponse.setResponseMessage("unable to satisfy your request!");
                apiResponse.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
                responseEntity=new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
    }

    @GetMapping("/")
    ResponseEntity getOrders(){
        ResponseEntity responseEntity;
        ApiResponse<List<OrderDTO>> apiResponse=new ApiResponse<>();
            apiResponse.setData(orderService.getAll().stream().map(EntityUtils::orderToOrderDTO).collect(Collectors.toList()));
            apiResponse.setResponseMessage("successfully returned all available orders !");
            apiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            responseEntity=new ResponseEntity(apiResponse, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping
    ResponseEntity updateOrder(@RequestBody OrderDTO orderDAO){
        ResponseEntity responseEntity;
        ApiResponse<OrderDTO> apiResponse=new ApiResponse<>();
        try {
            apiResponse.setData(EntityUtils.orderToOrderDTO(orderService.updateOrder(orderDAO)));
            apiResponse.setResponseMessage("successfully updated your order !");
            apiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            responseEntity=new ResponseEntity(apiResponse, HttpStatus.OK);
        }catch(BusinessException exception){
            apiResponse.setResponseMessage(exception.getMessage());
            exception.setErrors(exception.getErrors());
            apiResponse.setResponseMessage("unable to satisfy your request!");
            apiResponse.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
            responseEntity=new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }
}
