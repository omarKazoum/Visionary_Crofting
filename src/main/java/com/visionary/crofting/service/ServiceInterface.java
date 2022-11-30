package com.visionary.crofting.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceInterface<T> {

    void save(T object) throws Exception;
    T find(String uuid) throws Exception;
    List<T> getProductRequests() throws Exception;
    void update(Long id);
    void delete(Long id);
}
