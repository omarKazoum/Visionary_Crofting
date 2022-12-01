package com.visionary.crofting.service.Impl;

import com.visionary.crofting.entity.Product;
import com.visionary.crofting.entity.Stock;
import com.visionary.crofting.repository.StockRepository;
import com.visionary.crofting.response.ApiResponse;
import com.visionary.crofting.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StockService implements IService {
    @Autowired
    StockRepository stockRepository;
    @Override
    public ApiResponse<Stock> save(Object request) throws Exception {
        try {
            Stock stockrequest = new Stock();
            stockrequest = (Stock) request ;
            ApiResponse<Stock> stockApiResponse = new ApiResponse<>();
            boolean dataIsValid =this.validateStock(stockrequest);
            if( !dataIsValid){
                stockApiResponse.setResponseCode(ApiResponse.ResponseCode.VALIDATION_ERROR);
                stockApiResponse.setResponseMessage("data invalid");
                return stockApiResponse;
            }
            Stock stock = new Stock();
            stock.setName(stockrequest.getName());
            stock.setEmail(stockrequest.getEmail());
            stock.setPassword(stockrequest.getPassword());
            stock.setPhone(stockrequest.getPhone());
            stock.setAddress(stockrequest.getAddress());
            stockRepository.save(stock);
            for (Product productRequest : stockrequest.getProducts()) {
                Product product = new Product();
                product.setReference(productRequest.getReference());
                product.setTitle(productRequest.getTitle());
                product.setDescription(productRequest.getDescription());
                product.setInitialPrice(productRequest.getInitialPrice());
                product.setQuantity(productRequest.getQuantity());
                product.setStock(stock);
                //ticketAttachmentRepository.save(product);
            }
            stockApiResponse.setResponseCode(ApiResponse.ResponseCode.SUCCESS);
            stockApiResponse.setData(stock);
            return stockApiResponse;
        }catch (Exception e){
            ApiResponse<Stock> stockApiResponse = new ApiResponse<>();
            stockApiResponse.setResponseCode(ApiResponse.ResponseCode.ERROR_TECHNIQUE);
            return stockApiResponse;
        }
    }

    @Override
    public ApiResponse<Stock> find(String uuid) throws Exception {
        return null;
    }

    @Override
    public ApiResponse<List<Stock>> findAll() throws Exception {
        return null;
    }

    @Override
    public ApiResponse<Stock> delete(String uuid) throws Exception {
        return null;
    }

    @Override
    public ApiResponse<Stock> update(String uuid, Object Request) throws Exception {
        return null;
    }

    public boolean validateStock(Stock request){
        Pattern emailPattern =Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        if (!Objects.isNull(request)){
            Matcher emailMatcher = emailPattern.matcher(request.getEmail());
            if(!emailMatcher.matches() || request.getEmail().isEmpty() || request.getEmail().isBlank() ){
                return false;
            }
            if( request.getName().isEmpty() || request.getName().isBlank()){
                return false;
            }
            if(request.getPhone().isEmpty() || request.getPhone().isBlank() ){
                return false;
            }
            if(request.getPassword().isEmpty() || request.getPassword().isBlank() ){
                return false;
            }
            if(request.getAddress().isEmpty() || request.getAddress().isBlank() ){
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
}
