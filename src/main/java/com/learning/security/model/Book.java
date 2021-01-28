package com.learning.security.model;

import lombok.Data;

@Data
public class Book {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
