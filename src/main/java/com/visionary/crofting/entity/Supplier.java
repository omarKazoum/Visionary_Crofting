package com.visionary.crofting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends User{
    @OneToMany(mappedBy = "supplier")
    private List<SupplyRequest> supplyRequests;
    protected RoleEnum role = RoleEnum.SUPPLIER;

}
