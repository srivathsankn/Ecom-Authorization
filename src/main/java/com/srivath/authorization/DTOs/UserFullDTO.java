package com.srivath.authorization.DTOs;

import com.srivath.authorization.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserFullDTO {
    private String userName;
    private String email;
    private String password;
    private List<String> roles;

}
