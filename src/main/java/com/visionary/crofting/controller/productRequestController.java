package com.visionary.crofting.controller;

import com.visionary.crofting.entity.ProductRequest;
import com.visionary.crofting.service.productOffer.ProductRequestService;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/show/{requestReference}")
    public ProductRequest getRequestByReference(@PathVariable("requestReference") String reference){
        return productRequestService.findByReference(reference);
    }

    @PostMapping("/add")
    public void createNewRequest(@RequestBody ProductRequest productRequest)  { productRequestService.save(productRequest); }

    @PutMapping("/update")
    public void updateRequest(@RequestBody ProductRequest productRequest) { productRequestService.update(productRequest); }
    @PatchMapping("/set-supplier")
    public void setRequestSupplier(@RequestBody ProductRequest productRequest){ productRequestService.setSupplier(productRequest); }

    @DeleteMapping(path = "/delete/{requestId}")
    public void deleteRequest(@PathVariable("requestId") Long id){
        productRequestService.delete(id);
    }

}