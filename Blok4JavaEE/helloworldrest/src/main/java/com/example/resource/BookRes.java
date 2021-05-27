package com.example.resource;

import com.example.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("books")
public class BookRes {

    //Dit is puur om data te genereren.
    private final Book.BookBuilder bb = Book.builder();
    private List<Book> boekenKast = new ArrayList<>(List.of(
            bb.id(1).title("Outlander").author("D. Gabaldon").build(),
            bb.id(2).title("ACOTAR").author("Sarah J, Maas").build(),
            bb.id(3).title("From Blood and Ash").author("J. Armentrout").build()
    ));

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> get(@QueryParam("title") String zoekWoord) {

        return zoekWoord == null ?
                boekenKast:
                boekenKast.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(zoekWoord.toLowerCase()))
                    .collect(Collectors.toList());
    }

    @GET @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getById(@PathParam("id") Integer id) {
        return boekenKast.stream()
                .filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postBook(Book book) {
        boekenKast.add(book);
        return String.format("Book %s is toegevoegd aan uw boekenkast", book.getTitle());
    }
}
