package com.visionary.crofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name" , nullable = false)
    private String name ;
    @Column(name = "address" ,nullable = false,unique = true)
    private String address;
    @Column(nullable = false,unique = true)
    private String phone;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false )
    private String password;

    @OneToMany(mappedBy = "stock" )
    @JsonIgnore
    @JsonSetter
    private List<Product> products ;

    @OneToMany(mappedBy = "stock")
    @JsonIgnore
    @JsonSetter
    private List<SupplyRequest> supplyRequests;




}
