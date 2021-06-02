package com.example.resource;

import com.example.model.Book;
import com.example.util.JsonRes;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public class BookRes implements JsonRes {

    @GET
    public Book getBookById(@PathParam("id") int id) {
        Book book = new Book(2, "verzinsel", "verzinsel", "verzinsel");
        return book;
        //return "Ik ben in SUBRECROUSE met id" + id;
    }
}
