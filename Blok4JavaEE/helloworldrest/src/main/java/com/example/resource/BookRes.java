package com.example.resource;

import com.example.data.BookDao;
import com.example.data.IBookDao;
import com.example.model.Book;
import com.example.util.JsonRes;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public class BookRes implements JsonRes {
    @Inject
    private IBookDao iBookDao;

    @GET
    public Book getBookById(@PathParam("id") int id) {
        //return new Book(id, "verzinsel", "verzinsel", "verzinsel");
        return iBookDao.findBookById(id).orElseThrow(() -> new BadRequestException("Boek met dit id: " + id + " bestaat niet"));
    }
}
