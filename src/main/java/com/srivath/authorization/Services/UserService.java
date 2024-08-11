package com.srivath.authorization.Services;

import com.srivath.authorization.DTOs.UserFullDTO;
import com.srivath.authorization.Exceptions.RoleNotFoundException;
import com.srivath.authorization.Exceptions.UserNotFoundException;
import com.srivath.authorization.Models.Role;
import com.srivath.authorization.Models.User;
import com.srivath.authorization.DTOs.UserDTO;
import com.srivath.authorization.Repositories.RoleRepository;
import com.srivath.authorization.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getUserDetails(String email)  throws  UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent())
            return UserDTO.from(user.get());
        else
            throw new UserNotFoundException("User with email" + email + "not found");
    }

    public UserDTO signUp(UserFullDTO userFullDTO) throws RoleNotFoundException {
        String hashedPassword = this.passwordEncoder.encode(userFullDTO.getPassword());
        User user = new User(userFullDTO.getUserName(), userFullDTO.getEmail(), hashedPassword);

        ArrayList<Role> roles = new ArrayList<>();
        user.setRoles(roles);

//        for (String role: userFullDTO.getRoles()) {
//            Role definedRole = roleRepository.findByRoleName(role).orElseThrow(() -> new RoleNotFoundException("Role" + role + " not found"));
//            roles.add(definedRole);
//        }
        User savedUser = userRepository.save(user);
        return UserDTO.from(savedUser);
    }

    public UserDTO updateUserDetails(UserDTO userDTO) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByEmail( userDTO.getEmail());
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("User with email id " + userDTO.getEmail() + " not found!!! Please signup first!" );

        User user = optionalUser.get();
        if (userDTO.getUserName() != null)
            user.setUserName(userDTO.getUserName());

        if (!userDTO.getRoles().isEmpty())
        {
            user.setRoles(userDTO.getRoles());
            List<Role> roles = user.getRoles();
            for (int i = 0; i < roles.size(); i++) {
                Role role = roles.get(i);
                Optional<Role> role1 = this.roleRepository.findByRoleName(role.getRoleName());
                if (role1.isPresent()) {
                    roles.set(i,role1.get());
                } else {
                    roles.set(i,this.roleRepository.save(role));
                }
            }
            user.setRoles(roles);
        }

        User updatedUser = this.userRepository.save(user);

        return UserDTO.from(updatedUser);
    }
}
