package com.example.data;

import com.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDao {

    List<Book> getAllBooks();
    Book addBook(Book book);
    Optional<Book> findBookById(int id);

}
