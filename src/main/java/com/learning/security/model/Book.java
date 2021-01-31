package com.learning.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Book(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
