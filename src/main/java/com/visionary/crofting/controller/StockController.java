package com.visionary.crofting.controller;

import com.visionary.crofting.entity.Stock;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockController {

    @Qualifier("stockService")
    @Autowired
    IService service;

    @PostMapping("/stock")
    public ResponseEntity<ApiResponse<Stock>> saveStock(@RequestBody Stock stock){
        try {
            ApiResponse<Stock> response = service.save(stock);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            ApiResponse<Stock> stockApiResponse = new ApiResponse<>();
            stockApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return  new ResponseEntity<>(stockApiResponse, HttpStatus.OK) ;
        }
    }
}
