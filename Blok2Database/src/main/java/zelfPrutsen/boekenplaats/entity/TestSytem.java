package zelfPrutsen.boekenplaats.entity;

import javax.persistence.*;

public class TestSytem {
    //@PersistenceUnit(unitName = "Blok2Datebase")
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("TestNaam");

    public static void main(String[] args) {
        addPerson("Jamie", "Fraiser");
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addPerson(String fname, String lname ) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Person person = new Person();
            person.setFname(fname);
            person.setLname(lname);
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
