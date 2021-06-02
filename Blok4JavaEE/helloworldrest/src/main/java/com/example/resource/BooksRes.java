package com.example.resource;

import com.example.data.BookDao;
import com.example.model.Book;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("books")
public class BooksRes {

    @Inject
    private BookDao bookDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll(@QueryParam("title") String zoekWoord) {
        return bookDao.getAllBooks();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book addBook(Book book) {
        return bookDao.addBook(book);
    }

    //..../books/1
    //Niet annoteren met http methodes zoals get/post/put/patch/delete enz.
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") int id) {
        Optional<Book> book = bookDao.findBookById(id);
        return book == null? null: book.get();
        //return new BookRes().getById(id);
    }


}
