package zelfPrutsen.boekenplaats.entity;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    @OneToOne
    private Book book;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int custID) {
        this.id = custID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
