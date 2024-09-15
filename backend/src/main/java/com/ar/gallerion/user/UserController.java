package com.ar.gallerion.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.gallerion.exception.ApiRequestException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1")
class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    ResponseEntity<String> registerUser(@Valid @RequestBody UserModelDTO userModelDTO) {
        if (userService.isRegistrationSuccessful(userModelDTO)) {
            return ResponseEntity.ok().build();
        }

        throw new ApiRequestException("USER FAILED TO REGISTER!");
    }
}
