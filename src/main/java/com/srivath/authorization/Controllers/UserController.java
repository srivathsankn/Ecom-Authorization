package com.srivath.authorization.Controllers;

import com.srivath.authorization.DTOs.UserEmailDTO;
import com.srivath.authorization.Exceptions.RoleNotFoundException;
import com.srivath.authorization.Exceptions.UserNotFoundException;
import com.srivath.authorization.DTOs.UserDTO;
import com.srivath.authorization.DTOs.UserFullDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.srivath.authorization.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/details")
    public ResponseEntity<UserDTO> getUserDetails(@RequestBody UserEmailDTO userEmailDTO) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserDetails(userEmailDTO.getEmail()));
    }

    @PutMapping("/details")
    public ResponseEntity<UserDTO> updateUserDetails(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUserDetails(userDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserFullDTO userFullDTO) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.signUp(userFullDTO));
    }

}
