package com.example.data;

import com.example.model.Book;

import java.util.List;

public interface IBookDao {

    List<Book> getAllBooks();
    Book addBook(Book book);

}
