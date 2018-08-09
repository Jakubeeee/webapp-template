package com.jakubeeee.security.service;

import com.jakubeeee.security.exceptions.ValidationException;
import com.jakubeeee.security.persistence.entities.User;
import com.jakubeeee.security.persistence.repositories.UserRepository;
import com.jakubeeee.security.validation.forms.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.jakubeeee.security.persistence.entities.Role.Type.BASIC_USER;

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

    @Transactional
    public void createUser(String username, String password, String email) {
        var user = new User(username, password, email);
        roleService.grantRoles(user, BASIC_USER);
        encodePassword(user);
        userRepository.save(user);
        securityService.authenticate(user);
    }

    @Transactional
    public void createUser(SignUpForm form) throws ValidationException {
        validateFormData(form);
        User user = buildUserUsingFormData(form);
        roleService.grantRoles(user, BASIC_USER);
        encodePassword(user);
        userRepository.save(user);
        securityService.authenticate(user);
    }

    private void validateFormData(SignUpForm form) throws ValidationException {
        boolean isUsernameUnique = isUsernameUnique(form.getUsername());
        if (!isUsernameUnique) throw new ValidationException("Username already registered");
        boolean isEmailUnique = isEmailUnique(form.getEmail());
        if (!isEmailUnique) throw new ValidationException("Email already registered");
    }

    private User buildUserUsingFormData(SignUpForm form) {
        var user = new User();
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

    public boolean isUsernameUnique(String username) {
        Optional<User> userO = userRepository.findByUsername(username);
        return !userO.isPresent();
    }

    public boolean isEmailUnique(String email) {
        Optional<User> userO = userRepository.findByEmail(email);
        return !userO.isPresent();
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
