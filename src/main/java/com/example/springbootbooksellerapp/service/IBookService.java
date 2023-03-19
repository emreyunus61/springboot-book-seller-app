package com.example.springbootbooksellerapp.service;

import com.example.springbootbooksellerapp.model.Book;

import java.util.List;

public interface IBookService {
    Book saveBook(Book book);

    void deleteBook(Long id);

    List<Book> findAllBooks();
}
