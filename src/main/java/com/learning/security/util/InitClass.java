package com.learning.security.util;

import com.learning.security.model.Book;
import com.learning.security.repository.BookRepository;
import com.learning.security.user.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitClass implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) {
        Book book1 = new Book("Pirma");
        Book book2 = new Book("Antra");
        bookRepository.save(book1);
        bookRepository.save(book2);

        AuthoritiesEntity userAuthorities = new AuthoritiesEntity("GET_BOOK", "ROLE_USER");
        AuthoritiesEntity adminAuthorities1 = new AuthoritiesEntity("MANAGE_BOOK", "ROLE_ADMIN");
        AuthoritiesEntity adminAuthorities2 = new AuthoritiesEntity("GET_BOOK", "ROLE_ADMIN");
        authoritiesRepository.save(userAuthorities);
        authoritiesRepository.save(adminAuthorities1);
        authoritiesRepository.save(adminAuthorities2);


        UserEntity user = new UserEntity("user", "$2y$12$nH.K1M8QRXnlwr1APHFI5uYzp1pLtGdGGtTsjczesQ0zI4VN5Ypfm", true);
        UserEntity admin = new UserEntity("admin", "$2y$12$nH.K1M8QRXnlwr1APHFI5uYzp1pLtGdGGtTsjczesQ0zI4VN5Ypfm", true);

        UserRoleEntity userRoleEntity = new UserRoleEntity("ROLE_USER", user);
        Set<UserRoleEntity> userRolesSet = new HashSet<>();
        userRolesSet.add(userRoleEntity);
        user.setUserRoleEntities(userRolesSet);
        userRoleRepository.save(userRoleEntity);

        UserRoleEntity adminRoleEntity = new UserRoleEntity("ROLE_ADMIN", admin);
        Set<UserRoleEntity> adminRolesSet = new HashSet<>();
        adminRolesSet.add(adminRoleEntity);
        admin.setUserRoleEntities(adminRolesSet);
        userRoleRepository.save(adminRoleEntity);
    }
}
