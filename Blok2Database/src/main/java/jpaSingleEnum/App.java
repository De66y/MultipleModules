package jpaSingleEnum;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class App {

    private static final EntityManager em = Persistence.createEntityManagerFactory("ProductieJpa2").createEntityManager();


    public static void main(String[] args) {

        Book book = new Book("ACTOAR");
        em.getTransaction().begin();
        book.setClassification(Classification.NEWADULT);
        em.persist(book);
        em.getTransaction().commit();
        System.out.println(book);
    }
}
