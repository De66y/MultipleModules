package com.example.resource;

import com.example.data.BookDao;
import com.example.model.Book;
import com.example.util.JsonRes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public class BookRes implements JsonRes {
    @Inject
    private BookDao bookDao;

    @GET
    public Book getBookById(@PathParam("id") int id) {
        /*Book book = new Book(2, "verzinsel", "verzinsel", "verzinsel");
        return book;*/
        return bookDao.findBookById(id).get();
    }
}
