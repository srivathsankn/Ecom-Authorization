package com.srivath.authorization.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.srivath.authorization.Repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    CustomGrantedAuthority()
    {

    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
