package com.ar.gallerion.user;

import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.gallerion.exception.ApiRequestException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1")
class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody UserModelDTO userModelDTO,
            HttpServletRequest httpServletRequest) {
        if (userService.isRegistrationSuccessful(userModelDTO)) {
            Map<String, String> hmap = new HashMap<>();
            hmap.put("OUTPUT", "IT WORKS");

            return ResponseEntity.ok().body(hmap);
        }

        throw new ApiRequestException("USER FAILED TO REGISTER!");
    }

    @GetMapping("/try")
    ResponseEntity<String> test() {
        return ResponseEntity.ok().body("TESTING");
    }
}
