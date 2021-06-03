package com.example.resource;

import com.example.data.BookDao;
import com.example.data.IBookDao;
import com.example.model.Book;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("books")
public class BooksRes {

    @Inject
    private IBookDao iBookDao;

    @Inject
    private BookRes bookRes;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll(@QueryParam("title") String zoekWoord) {
        return iBookDao.getAllBooks();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book addBook(Book book) {
        return iBookDao.addBook(book);
    }

    //..../books/1
    //Niet annoteren met http methodes zoals get/post/put/patch/delete enz.
    @Path ("{id}")
    public BookRes getBookRes(@PathParam("id") int id) {
        return bookRes;
    }


}
