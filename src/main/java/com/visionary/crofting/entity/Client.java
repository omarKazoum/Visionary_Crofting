package com.visionary.crofting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User{
    @OneToMany(mappedBy = "client")
    private List<Order> orders;
    private RoleEnum role = RoleEnum.CLIENT;

}
