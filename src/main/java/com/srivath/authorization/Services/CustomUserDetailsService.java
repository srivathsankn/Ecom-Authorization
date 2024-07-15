package com.srivath.authorization.Services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.srivath.authorization.Models.CustomGrantedAuthority;
import com.srivath.authorization.Models.CustomUserDetails;
import com.srivath.authorization.Models.User;
import com.srivath.authorization.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User" + username + " not found");
        }
        return new CustomUserDetails(user.get());
    }
}
