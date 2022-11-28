package com.visionary.crofting.service;

import com.visionary.crofting.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface IService<T,R>  {
    ApiResponse<T> save(R Request) throws Exception;
    ApiResponse<T> find(String uuid) throws Exception;

}
