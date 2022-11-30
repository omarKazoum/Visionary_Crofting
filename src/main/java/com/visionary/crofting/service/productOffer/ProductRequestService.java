package com.visionary.crofting.service.productOffer;

import com.visionary.crofting.entity.ProductRequest;
import com.visionary.crofting.repository.ProductRequestRepository;
import com.visionary.crofting.service.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        System.out.println("product request was recorded successfully into db");
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
    @Transactional
    public void update(ProductRequest data) {
        System.out.println(data.getQuantity());
        Long id = data.getId();
       ProductRequest productRequest = productRequestRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("the product request with id " + id + " is not found to be updated"));
       // Setting the new data given from the json object to the product request object
       productRequest.setProductReference(data.getProductReference());
       productRequest.setQuantity(data.getQuantity());
       productRequest.setStock(data.getStock());
       productRequest.setStatus(data.getStatus());
       productRequest.setSupplier(data.getSupplier());
        System.out.println("Updated successfuly");
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

