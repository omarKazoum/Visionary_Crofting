package com.visionary.crofting.service;

import com.visionary.crofting.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T,R>  {
    ApiResponse<T> save(R Request) throws Exception;
    ApiResponse<T> find(String uuid) throws Exception;
    ApiResponse<List<T>> findAll() throws Exception;
    ApiResponse<T> delete(String uuid) throws Exception;
    ApiResponse<T> update(String uuid,R Request) throws Exception;


}
