package com.visionary.crofting.controller;

import com.visionary.crofting.entity.Supplier;
import com.visionary.crofting.requests.SupplierRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class SupplierController {

    @Qualifier("supplierService")
    @Autowired
    IService service ;

    @PostMapping("/supplier")
    public ResponseEntity<ApiResponse<Supplier>> saveSupplier(@RequestHeader Map<String, String> headers , @RequestBody SupplierRequest supplierRequest ) throws Exception{
        try {
            ApiResponse<Supplier> response = service.save(supplierRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>() ;
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(supplierApiResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/supplier/{uuid}")
    public ResponseEntity<ApiResponse<Supplier>> getSupplierById(@PathVariable String uuid , @RequestHeader Map<String, String> headers )throws Exception{
        try {
            ApiResponse<Supplier> response = service.find(uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>() ;
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(supplierApiResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/suppliers")
    ResponseEntity<ApiResponse<List<Supplier>>> getListSuppliers()throws Exception {
        try {
            ApiResponse<List<Supplier>> response = service.findAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<Supplier>> supplierApiResponse = new ApiResponse<>();
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(supplierApiResponse, HttpStatus.OK);
        }
    }


    @DeleteMapping("/supplier/{uuid}")
    public ResponseEntity<ApiResponse<Supplier>> deleteSupplierById(@PathVariable String uuid){
        try {
            ApiResponse<Supplier> response = service.delete(uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>() ;
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(supplierApiResponse, HttpStatus.OK);
        }
    }

    @PutMapping("/supplier/{uuid}")
    public ResponseEntity<ApiResponse<Supplier>> updateSupplier(@PathVariable String uuid,@RequestBody SupplierRequest supplierRequest){
        try {
            ApiResponse<Supplier> response = service.update(uuid,supplierRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Supplier> supplierApiResponse = new ApiResponse<>() ;
            supplierApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(supplierApiResponse, HttpStatus.OK);
        }
    }




}
