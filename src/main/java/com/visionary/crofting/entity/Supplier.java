package com.visionary.crofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "supplier")
public class Supplier extends User{
    @OneToMany(mappedBy = "supplier")
    private List<ProductRequest> supplyRequests;

    public Supplier(String name, String email, String password, String phone) {
        super(name, email, password, phone);
        setRole(RoleEnum.SUPPLIER);
        setUuid(UUID.randomUUID().toString());
    }

    public Supplier() {
        setRole(RoleEnum.SUPPLIER);
    }

    @JsonIgnore
    public List<ProductRequest> getSupplyRequests() {
        return supplyRequests;
    }

    @JsonSetter
    public void setSupplyRequests(List<ProductRequest> supplyRequests) {
        this.supplyRequests = supplyRequests;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
