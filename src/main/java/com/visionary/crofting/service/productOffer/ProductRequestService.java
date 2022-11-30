package com.visionary.crofting.service.productOffer;

import com.visionary.crofting.entity.ProductRequest;
import com.visionary.crofting.repository.ProductRequestRepository;
import com.visionary.crofting.service.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRequestService implements ServiceInterface<ProductRequest> {

    ProductRequestRepository productRequestRepository;

    public ProductRequestService(ProductRequestRepository productRequestRepository) {
        this.productRequestRepository = productRequestRepository;
    }

    @Override
    public void save(ProductRequest productRequest) {
        productRequestRepository.save(productRequest);
    }

    @Override
    public ProductRequest find(String uuid) {
        return null;
    }

    @Override
    public List<ProductRequest> getProductRequests() {
        List<ProductRequest> productRequests = productRequestRepository.findAll();
        return productRequests;
    }

    @Override
    public void update(Long id) {
    }

    @Override
    public void delete(Long id) {
        boolean isExists = productRequestRepository.existsById(id);
        if( !isExists) {
            throw new IllegalStateException("The id : " + id + " does not exists in database");
        }
        productRequestRepository.deleteById(id);
        System.out.println("***************** deleted Successfully ******************");
    }
}

