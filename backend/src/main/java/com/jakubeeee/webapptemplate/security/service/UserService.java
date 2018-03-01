package com.jakubeeee.webapptemplate.security.service;

import com.jakubeeee.webapptemplate.security.persistence.entities.User;
import com.jakubeeee.webapptemplate.security.persistence.repositories.UserRepository;
import com.jakubeeee.webapptemplate.security.validation.forms.SignUpForm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.jakubeeee.webapptemplate.security.persistence.entities.Role.Type.BASIC_USER;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Getter
    private List<String> errorMessages = new ArrayList<>();

    @Transactional
    public void createUser(String username, String password, String email) {
        User user = new User(username, password, email);
        roleService.grantRoles(user, BASIC_USER);
        encodePassword(user);
        userRepository.save(user);
        securityService.authenticate(user);
    }

    @Transactional
    public void createUser(SignUpForm form) {
        validateFormData(form);
        User user = buildUserUsingFormData(form);
        roleService.grantRoles(user, BASIC_USER);
        encodePassword(user);
        userRepository.save(user);
        securityService.authenticate(user);
    }

    private void validateFormData(SignUpForm form) {
        Optional<User> userO;
        userO = userRepository.findByUsername(form.getUsername());
        if (userO.isPresent())
            errorMessages.add("Username already taken");
        userO = userRepository.findByEmail(form.getEmail());
        if (userO.isPresent())
            errorMessages.add("Email already taken");
    }

    private User buildUserUsingFormData(SignUpForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return user;
    }

    private void encodePassword(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }

    @Transactional
    public void updateUserPassword(long userId, String password) {
        password = passwordEncoder.encode(password);
        userRepository.updateUserPassword(userId, password);
    }

    public User findById(long id) {
        Optional<User> userO = userRepository.findById(id);
        return userO.orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found in the database"));
    }

    public User findByUsername(String username) {
        Optional<User> userO = userRepository.findByUsername(username);
        return userO.orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found in the database"));
    }

    public User findByEmail(String email) {
        Optional<User> userO = userRepository.findByEmail(email);
        return userO.orElseThrow(() -> new NoSuchElementException("User with email " + email + " not found in the database"));
    }

}
