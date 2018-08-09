package com.jakubeeee.security.persistence.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Getter
    @AllArgsConstructor
    public enum Type {
        BASIC_USER(1), PRO_USER(2), ADMIN(3);
        final long id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    String name;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

}
