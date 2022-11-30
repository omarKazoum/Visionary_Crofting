package com.visionary.crofting.controller;

import com.visionary.crofting.entity.ProductRequest;
import com.visionary.crofting.service.productOffer.ProductRequestService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping("api/request-order")
public class productRequestController {
    private ProductRequestService productRequestService;

    public productRequestController(ProductRequestService productRequestService) {
        this.productRequestService = productRequestService;
    }

    @GetMapping("/show")
    public List<ProductRequest> getProductRequests()  {
            return productRequestService.getProductRequests();
    }

    @PostMapping("/add")
    public void createNewRequest(@RequestBody ProductRequest productRequest)  {
        productRequestService.save(productRequest);
    }

    @PutMapping("/update")
    public void updateStudent(@RequestBody ProductRequest productRequest) {
        productRequestService.update(productRequest);
    }

    @DeleteMapping(path = "/delete/{requestId}")
    public void deleteStudent(@PathVariable("requestId") Long id){
        productRequestService.delete(id);
    }


}
