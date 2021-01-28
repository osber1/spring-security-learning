package com.learning.security.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private User user = null;

    public UserService() {
        user = new User("osvas", "$2y$12$PgTzTjZ7hcHrVoQNaOFLqe3VZgP71SQeAeoU/cTPmxqmku1gqF/L.", true);
    }

    public User getUserByUserName(String username) {
        if (user.getUsername().equals(username)) {
            return user;
        } else {
            return null;
        }

    }
}
