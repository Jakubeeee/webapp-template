package com.jakubeeee.security.persistence.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken implements Serializable {

    final static int DEFAULT_TOKEN_LIFETIME = 120;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NonNull
    @Column(unique = true)
    String value;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @NonNull
    @Column(name = "expiry_date")
    LocalDateTime expiryDate;

    public PasswordResetToken(String value, User user, LocalDateTime creationDateTime, int expirationInMinutes) {
        this.value = value;
        this.user = user;
        if (expirationInMinutes == 0) expirationInMinutes = DEFAULT_TOKEN_LIFETIME;
        expiryDate = creationDateTime.plusMinutes(expirationInMinutes);
    }

}
