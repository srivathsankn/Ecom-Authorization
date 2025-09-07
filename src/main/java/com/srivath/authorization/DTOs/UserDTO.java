package com.srivath.authorization.DTOs;

import com.srivath.authorization.Models.Role;
import com.srivath.authorization.Models.User;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String email;
    private String phoneNumber;
    @OneToMany
    private List<Role> roles;

    public static UserDTO from(User user) {
        return new UserDTO(user.getUserName(), user.getEmail(), user.getPhoneNumber(), user.getRoles());
    }
}
