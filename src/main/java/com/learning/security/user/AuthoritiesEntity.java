package com.learning.security.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authorities")
@NoArgsConstructor
public class AuthoritiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private int authorityId;
    private String authority;
    private String role;

    public AuthoritiesEntity(String authority, String role) {
        this.authority = authority;
        this.role = role;
    }
}
