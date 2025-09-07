package com.srivath.authorization.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    @ManyToMany (fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {

    }
    public User(String name, String email, String phoneNumber, String password) {
        this.userName = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
