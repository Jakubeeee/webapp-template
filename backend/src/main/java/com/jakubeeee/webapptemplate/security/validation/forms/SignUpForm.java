package com.jakubeeee.webapptemplate.security.validation.forms;

import com.jakubeeee.webapptemplate.security.validation.annotations.FieldMatch;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirm")
})

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SignUpForm implements Serializable {

    @NotBlank
    @Size(min = 8, max = 30)
    String username;

    @NotBlank
    @Size(max = 50)
    @Email
    String email;

    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$")
    String password;

    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$")
    String passwordConfirm;

}
