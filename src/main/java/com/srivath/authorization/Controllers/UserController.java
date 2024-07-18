package com.srivath.authorization.Controllers;

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

    @GetMapping("/details/{email}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserDetails(email));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserFullDTO userFullDTO) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.signUp(userFullDTO));
    }

}
