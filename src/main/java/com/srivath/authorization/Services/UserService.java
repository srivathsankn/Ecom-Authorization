package com.srivath.authorization.Services;

import com.srivath.authorization.DTOs.UserFullDTO;
import com.srivath.authorization.Exceptions.RoleNotFoundException;
import com.srivath.authorization.Exceptions.UserNotFoundException;
import com.srivath.authorization.Models.Role;
import com.srivath.authorization.Models.User;
import com.srivath.authorization.DTOs.UserDTO;
import com.srivath.authorization.Repositories.RoleRepository;
import com.srivath.authorization.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository ;
    private RoleRepository roleRepository;

    UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO getUserDetails(String email)  throws  UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent())
            return UserDTO.from(user.get());
        else
            throw new UserNotFoundException("User with email" + email + "not found");
    }

    public UserDTO signUp(UserFullDTO userFullDTO) throws RoleNotFoundException {
        User user = new User(userFullDTO.getUserName(), userFullDTO.getEmail(), userFullDTO.getPassword());

        ArrayList<Role> roles = new ArrayList<>();
        user.setRoles(roles);

//        for (String role: userFullDTO.getRoles()) {
//            Role definedRole = roleRepository.findByRoleName(role).orElseThrow(() -> new RoleNotFoundException("Role" + role + " not found"));
//            roles.add(definedRole);
//        }
        User savedUser = userRepository.save(user);
        return UserDTO.from(savedUser);
    }
}
