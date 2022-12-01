package com.visionary.crofting.service;

import com.visionary.crofting.entity.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceInterface<T> {

    void save(T object) throws Exception;
    T find(String uuid) throws Exception;
    List<T> getProductRequests() throws Exception;
    void update(ProductRequest data);
    void setSupplier(ProductRequest data);
    void delete(Long id);
}
