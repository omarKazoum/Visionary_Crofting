package com.visionary.crofting.service.Impl;

import com.visionary.crofting.entity.Product;
import com.visionary.crofting.repository.ProductRepository;
import com.visionary.crofting.requests.ProductRequest;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService implements IService<Product, ProductRequest> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ApiResponse<Product> save(ProductRequest request) {
        try {
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            Product product = new Product();

            product.setReference(request.getReference());
            product.setTitle(request.getTitle());
            product.setDescription(request.getDescription());
            product.setInitialPrice(request.getInitialPrice());
            product.setQuantity(request.getQuantity());
            // product.setStock(request.getStock());

            productRepository.save(product);
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            return productApiResponse;
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return productApiResponse;
        }

    }

    @Override
    public ApiResponse<Product> find(String reference)  {
        try {
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            boolean validREF = validateREF(reference);
            if (!validREF){
                productApiResponse.setResponseCode(ApiResponse.ResponseCode.INVALID_TOKEN);
                productApiResponse.setResponseMessage("invalid product reference");
                return productApiResponse;
            }
            Product product = productRepository.findByReference(reference);
            if (!Objects.isNull(product)){
                productApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                productApiResponse.setResponseMessage("Product exist");
                productApiResponse.setData(product);
                return productApiResponse;
            }
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            productApiResponse.setResponseMessage("Product not exist");
            return productApiResponse;
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return productApiResponse;
        }
    }

    @Override
    public ApiResponse<List<Product>> findAll()  {
        List<Product> Products = productRepository.findAll();
        ApiResponse<List<Product>> productApiResponse = new ApiResponse<>();
        productApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
        productApiResponse.setResponseMessage("Product exist");
        productApiResponse.setData(Products);
        return productApiResponse;
    }

    @Override
    public ApiResponse<Product> delete(String reference)  {
        try {
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            ApiResponse<Product> productResponse = this.find(reference);
            if (!Objects.isNull(productResponse.getData())){
                productRepository.delete(productResponse.getData());
                productApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                productApiResponse.setResponseMessage("Product deleted");
                productApiResponse.setData(productApiResponse.getData());
                return productApiResponse;
            }
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            productApiResponse.setResponseMessage("Product not deleted");
            return productApiResponse;
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return productApiResponse;
        }
    }

    @Override
    public ApiResponse<Product> update(String reference, ProductRequest Request) {
        try {
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            ApiResponse<Product> productResponse = this.find(reference);
            if (!Objects.isNull(productResponse.getData())){
                if ( Request.getTitle() != null) {
                    productResponse.getData().setTitle(Request.getTitle());
                }
                if ( Request.getDescription() != null ) {
                    productResponse.getData().setDescription(Request.getDescription());
                }
                if ( Request.getInitialPrice() != 0.0f) {
                    productResponse.getData().setInitialPrice(Request.getInitialPrice());
                }
                if ( Request.getQuantity() != 0) {
                    productResponse.getData().setQuantity(Request.getQuantity());
                }
                // Need to add stock field
                productRepository.save(productResponse.getData());
                productApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
                productApiResponse.setResponseMessage("Product updated");
                productApiResponse.setData(productApiResponse.getData());
                return productApiResponse;
            }
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.NOT_EXIST);
            productApiResponse.setResponseMessage("Product not updated");
            return productApiResponse;
        }catch (Exception e){
            ApiResponse<Product> productApiResponse = new ApiResponse<>();
            productApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return productApiResponse;
        }
    }

    public boolean validateREF(String reference){
        Pattern refPattern =Pattern.compile("^[a-zA-Z]{3}\\d{3}$");
        Matcher refMatcher = refPattern.matcher(reference);
        if(!refMatcher.matches() || reference.isEmpty() || reference.isBlank() ){
            return false;
        }
        return true;
    }
}
