package com.boekenplaats.entity;

import javax.persistence.*;

@Entity
//@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custID;
    private String fname;
    private String lname;

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
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
