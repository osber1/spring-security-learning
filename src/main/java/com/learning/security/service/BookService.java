package com.learning.security.service;

import com.learning.security.model.Book;
import com.learning.security.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Collection<Book> getAll() {
        return repository.findAll();
    }

}
