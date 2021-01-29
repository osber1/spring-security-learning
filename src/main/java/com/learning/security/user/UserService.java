package com.learning.security.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User getUserByUserName(String username) {

        UserEntity user = repository.findByUsername(username);
        if (user != null) {
            return createUserFromUserEntity(user);
        } else {
            return null;
        }
    }

    private User createUserFromUserEntity(UserEntity user) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled());
    }
}
