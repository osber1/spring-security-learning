package com.learning.security.security;

import com.learning.security.user.UserService;
import com.learning.security.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByUserName(username);

        if (user != null) {
            return user;
        } else {
            log.info("username {} not found", username);
            throw new UsernameNotFoundException(username + " does not exist.");
        }
    }
}
