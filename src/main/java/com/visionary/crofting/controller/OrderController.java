package com.visionary.crofting.controller;

import com.visionary.crofting.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/orders")
public class OrderController {
    @GetMapping("/test")
    ApiResponse<String> test(){
        ApiResponse response=new ApiResponse<>();
        response.setResponseCode(ApiResponse.ResponseCode.SUCCESS);

        response.setData("great work");
        response.setResponseMessage("everything went well");
        return response;
    }

}
