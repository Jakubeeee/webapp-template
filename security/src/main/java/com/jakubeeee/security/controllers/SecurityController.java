package com.jakubeeee.security.controllers;

import com.jakubeeee.security.exceptions.ValidationException;
import com.jakubeeee.security.service.PasswordResetService;
import com.jakubeeee.security.service.SecurityService;
import com.jakubeeee.security.service.UserService;
import com.jakubeeee.security.validation.forms.ChangePasswordForm;
import com.jakubeeee.security.validation.forms.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class SecurityController {

    private final SecurityService securityService;

    private final UserService userService;

    private final PasswordResetService passwordResetService;

    @GetMapping("/isAuthenticated")
    public boolean isAuthenticated() {
        return securityService.isAuthenticated();
    }

    @PostMapping("/createAccount")
    public void handleCreateAccountRequest(@Validated @RequestBody SignUpForm form) throws ValidationException {
        userService.createUser(form);
    }

    @PostMapping(path = "/forgotMyPassword", consumes = "text/plain")
    public void handleForgotMyPasswordRequest(@RequestBody String email, HttpServletRequest request) {
        String origin = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        passwordResetService.handleForgotMyPasswordProcess(email, origin);
    }

    @PostMapping("/changePassword")
    public void handleChangePasswordRequest(@Validated @RequestBody ChangePasswordForm form) throws ValidationException {
        passwordResetService.changePassword(form);
    }

}
