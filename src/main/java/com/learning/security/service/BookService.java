package com.learning.security.service;

import com.learning.security.model.Book;
import com.learning.security.repository.BookRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Collection<Book> getAll() {
        return repository.findAll();
    }

    public Book add(Book book) {
        return repository.save(book);
    }

    public Book getOne(int id) throws NotFoundException {
        Optional<Book> book = repository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("There is no id: " + id);
        }
        return book.get();
    }
}