package com.visionary.crofting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id ;
    @Column(name = "uuid_user",length = 60,nullable = false,unique = true)
    private String uuid;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false , unique = true)
    private String email;
    @Column(name = "password", nullable = false )
    private String password;
    @Column(name = "phone", nullable = false , unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    protected  RoleEnum role;

    public enum RoleEnum{
        SUPPLIER,
        CLIENT
    }


}
