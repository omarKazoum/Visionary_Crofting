package com.visionary.crofting.requests;


import lombok.Data;
import org.springframework.data.annotation.Transient;

@Data
public class ClientRequest {
    @Transient
    private String name;
    private String email;
    private String password;
    private String phone;
}
