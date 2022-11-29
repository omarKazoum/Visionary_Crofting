package com.visionary.crofting.controller;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;
    @GetMapping("/test")
    ApiResponse<Order> test(){
        ApiResponse<Order> response=new ApiResponse<>();
        response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);

        response.setData(new Order());
        response.setResponseMessage("everything went well");
        return response;
    }

    @GetMapping
    ApiResponse<Order> getAll(){
        return new ApiResponse<>();
    }
}
