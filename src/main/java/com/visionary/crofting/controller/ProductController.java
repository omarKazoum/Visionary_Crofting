package com.visionary.crofting.controller;

import com.visionary.crofting.entity.Product;
import com.visionary.crofting.requests.ProductRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import com.visionary.crofting.service.Impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ProductController {

    @Qualifier("productService")
    @Autowired
    IService service ;

    @Autowired
    ProductService productService ;

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestBody ProductRequest productRequest ){
        try {
            ApiResponse<Product> response = service.save(productRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>() ;
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(productApiResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/product/{reference}")
    public ResponseEntity<ApiResponse<Product>> getProductByREF(@PathVariable String reference ){
        try {
            ApiResponse<Product> response = service.find(reference);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>() ;
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(productApiResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping("/product/{reference}")
    public ResponseEntity<ApiResponse<Product>> deleteProductById(@PathVariable String reference){
        try {
            ApiResponse<Product> response = service.delete(reference);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>() ;
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(productApiResponse, HttpStatus.OK);
        }
    }

    @PutMapping("/product/{reference}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable String reference,@RequestBody ProductRequest productRequest){
        try {
            ApiResponse<Product> response = service.update(reference,productRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>() ;
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return new ResponseEntity<>(productApiResponse, HttpStatus.OK);
        }
    }
}
