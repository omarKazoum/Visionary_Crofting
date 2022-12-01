package com.visionary.crofting.requests;


import lombok.Data;

@Data
public class ProductRequest {

    private String reference;
    private String title;
    private String description;
    private float initialPrice;
    private int quantity;
    private String stock;
}
