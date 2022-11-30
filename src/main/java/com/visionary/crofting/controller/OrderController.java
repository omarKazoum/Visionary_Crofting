package com.visionary.crofting.controller;

import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.exceptions.BusinessException;
import com.visionary.crofting.requests.OrderItemDTO;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IOrderItemService;
import com.visionary.crofting.service.IOrderService;
import com.visionary.crofting.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

import static com.visionary.crofting.util.EntityUtils.orderItemDTOToOrderItem;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

    //TODO add missing endpoints

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
  /*  @GetMapping("/")
    ResponseEntity getOrderItemsPerOrder(){
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
    }*/

}
