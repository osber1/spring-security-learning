package com.learning.security.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    private String username;
    private String password;
    private boolean enabled;
    @OneToMany(mappedBy = "userEntity",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<UserRoleEntity> userRoleEntities;

    public UserEntity(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
