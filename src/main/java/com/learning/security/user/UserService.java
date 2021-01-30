package com.learning.security.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public User getUserByUserName(String username) {

        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return createUserFromUserEntity(user);
        } else {
            return null;
        }
    }

    private User createUserFromUserEntity(UserEntity userEntity) {
        User user = new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled());
        Stream<Stream<String>> streamStreamAuths = userEntity.getUserRoleEntities().stream()
                .map(ur -> {
                    Set<AuthoritiesEntity> authoritiesEntities = authoritiesRepository.findByRole(ur.getRole());
                    return authoritiesEntities.stream().map(AuthoritiesEntity::getAuthority);
                });

        Set<String> authorities = streamStreamAuths.flatMap(authStream -> authStream)
                .collect(Collectors.toSet());

        userEntity.getUserRoleEntities().forEach(ur -> authorities.add(ur.getRole()));

        user.setAuthorities(authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet()));

        return user;
    }
}