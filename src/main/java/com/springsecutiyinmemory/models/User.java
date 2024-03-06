package com.springsecutiyinmemory.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
