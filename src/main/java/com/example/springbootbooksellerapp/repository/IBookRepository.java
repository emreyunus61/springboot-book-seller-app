package com.example.springbootbooksellerapp.repository;

import com.example.springbootbooksellerapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Long> {


}