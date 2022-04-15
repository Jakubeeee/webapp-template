package com.jakubeeee.security.controllers;

import com.jakubeeee.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ValidationController {

    private final UserService userService;

    @PostMapping(path = "/isUsernameUnique", consumes = "text/plain")
    public boolean checkUsernameUniqueness(@RequestBody String username) {
        return userService.isUsernameUnique(username);
    }

    @PostMapping(path = "/isEmailUnique", consumes = "text/plain")
    public boolean checkEmailUniqueness(@RequestBody String email) {
        return userService.isEmailUnique(email);
    }

}
