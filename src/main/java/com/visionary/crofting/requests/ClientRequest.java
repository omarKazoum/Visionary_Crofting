package com.visionary.crofting.requests;


import lombok.Data;

@Data
public class ClientRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
}
