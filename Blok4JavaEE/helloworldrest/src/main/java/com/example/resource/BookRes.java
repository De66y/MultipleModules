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
            bb.id(1).title("Outlander").author("D. Gabaldon").genre("Fantasy, History, Action, Romance").build(),
            bb.id(2).title("Shadowhunter").author("Sarah J, Maas").genre("Romance, Action, Fantasy, Fairytale retelling").build(),
            bb.id(3).title("From Blood and Ash").author("J. Armentrout").genre("Romance, Action").build()
    ));

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll(@QueryParam("title") String zoekWoord) {
        System.out.println("getall wordt aangeroepen");
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
        System.out.println("post wordt aangeroepen");
        boekenKast.add(book);
        return String.format("Book %s is toegevoegd aan uw boekenkast", book.getTitle());
    }
}
