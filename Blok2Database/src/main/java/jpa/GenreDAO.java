package jpa;

import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;

@Log4j2
public class GenreDAO {

    private final EntityManager em;

    public GenreDAO(EntityManager em) {
        this.em = em;
    }

    public void maakGenresAan() {
        opslaan(new Genre("Fantasy"));
        opslaan(new Genre("Romantiek"));
        opslaan(new Genre("Avontuurlijk"));
        opslaan(new Genre("Si-fi"));
    }

    public void opslaan(Genre genre) {
        try {
            em.getTransaction().begin();
            em.persist(genre);
            em.getTransaction().commit();
            log.info(String.format("%s is opgeslagen", genre.getOmschrijving()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }
    public Genre zoek(int id) {
        try {
            Genre genre = em.find(Genre.class, id);
            return genre;
        } catch (NullPointerException e) {
            log.warn("Book met id %s bestaat niet", id);
            return null;
        }

    }
}
