package com.example.data;

import com.example.model.Book;
import com.example.resource.BookRes;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

// @ApplicationScoped // Managed CDI bean, dus geen super powers
@Stateless //Ik maak hier een EJB van deze class/instantie
// Managed Enterprise Java Bean (EJB): hij krijgt super powers (zoals transaction capabilities).
// Stateless: de container maakt bij elk request een nieuwe instance;
// de class kan dus ook beter geen data-fields bevatten (dat heeft geen zin)!
@TransactionManagement(TransactionManagementType.CONTAINER)
// = default; whole annotation can be omitted when choosing CONTAINER
public class BookDao implements IBookDao{

    // STATE: doesn't make sense in Stateless EJB.
    // private String name;

    @PersistenceContext //Container managed EntityManager
    private EntityManager em;

    public List<Book> getAllBooks () {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) // = default; whole annotation can be
    // omitted when choosing REQUIRED.
    // Deze methode wordt in een databasetransactie op de server uitgevoerd.
    // Als er al een transactie loopt, gebruikt de server die, anders maakt hij een nieuwe transactie aan.
    public Book addBook(Book book) {
        em.persist(book);
        return book;
    }

    public Book findBookById(int id) {
      return new Book(2, "Hard gecodeerd boek", "uit de DAO", "");
    }

}
