package com.visionary.crofting.requests;

import lombok.Data;

@Data
public class SupplierRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
}
