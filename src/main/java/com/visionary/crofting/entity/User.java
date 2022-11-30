package com.visionary.crofting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    @Column(name = "uuid_user",length = 60,nullable = false,unique = true)
    private String uuid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false , unique = true)
    private String email;
    @Column( nullable = false )
    private String password;
    @Column( nullable = false , unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    private  RoleEnum role;

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public enum RoleEnum{
        SUPPLIER ,
        CLIENT
    }

}
