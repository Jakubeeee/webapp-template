package com.jakubeeee.security.validation.forms;

import com.jakubeeee.security.validation.annotations.FieldMatch;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@FieldMatch(first = "password", second = "passwordConfirm")
public class ChangePasswordForm implements Serializable {

    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$")
    String password;

    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$")
    String passwordConfirm;

    int userId;
    String resetToken;

}
