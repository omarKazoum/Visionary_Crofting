package com.visionary.crofting.controller;

import com.visionary.crofting.entity.ProductRequest;
import com.visionary.crofting.service.productOffer.ProductRequestService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    /*
    @PutMapping("update/{requestId}")
    public void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String name , @RequestParam(required = false) String email) throws IllegalAccessException {
        studentService.updateStudent(id,name,email);
    }

     */
    @DeleteMapping(path = "/delete/{requestId}")
    public void deleteStudent(@PathVariable("requestId") Long id){
        productRequestService.delete(id);
    }


}
