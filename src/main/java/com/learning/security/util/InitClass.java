package com.learning.security.util;

import com.learning.security.model.Book;
import com.learning.security.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class InitClass implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        Book book1 = new Book("Pirma", LocalDate.now());
        Book book2 = new Book("Antra", LocalDate.now());
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
