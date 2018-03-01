package com.jakubeeee.webapptemplate.security.controllers;

import com.jakubeeee.webapptemplate.security.service.PasswordResetService;
import com.jakubeeee.webapptemplate.security.service.SecurityService;
import com.jakubeeee.webapptemplate.security.service.UserService;
import com.jakubeeee.webapptemplate.security.validation.forms.ChangePasswordForm;
import com.jakubeeee.webapptemplate.security.validation.forms.SignUpForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordResetService passwordResetService;

    @GetMapping("/isAuthenticated")
    public boolean isAuthenticated() {
        return securityService.isAuthenticated();
    }

    @PostMapping("/createAccount")
    public ResponseEntity<List<String>> handleCreateAccountRequest(@Validated @RequestBody SignUpForm form) {
        userService.createUser(form);
        return new ResponseEntity<>(userService.getErrorMessages(), HttpStatus.OK);
    }

    @PostMapping(path = "/forgotMyPassword", consumes = "text/plain")
    public void handleForgotMyPasswordRequest(@RequestBody String email, HttpServletRequest request) {
        String origin = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        passwordResetService.handleForgotMyPasswordProcess(email, origin);
    }

    @PostMapping("/changePassword")
    public void handleChangePasswordRequest(@Validated @RequestBody ChangePasswordForm form) {
        passwordResetService.changePassword(form);
    }

}
