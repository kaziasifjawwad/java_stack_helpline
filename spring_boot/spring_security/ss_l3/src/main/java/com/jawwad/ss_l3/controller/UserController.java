package com.jawwad.ss_l3.controller;

import com.jawwad.ss_l3.domain.User;
import com.jawwad.ss_l3.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsManager userDetailsManager;

    @PostMapping("register")
    public ResponseEntity<Void> saveUser(UserRequest userRequest) {
        this.userDetailsManager.createUser(new User(userRequest));
        return ResponseEntity.ok().build();
    }
}
