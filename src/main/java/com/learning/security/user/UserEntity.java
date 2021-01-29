package com.learning.security.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String username;
    private String password;
    private boolean enabled;
}
