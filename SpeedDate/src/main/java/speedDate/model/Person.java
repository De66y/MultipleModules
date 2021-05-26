package speedDate.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String naam;
    private boolean satOnTheBench;
    private List<Person> myDateList;

    public Person() {
    }
    public Person(String naam) {
        this.naam = naam;
        this.myDateList = new ArrayList<>();
    }

    public String getNaam() {
        return naam;
    }

    public boolean isSatOnTheBench() {
        return satOnTheBench;
    }
    public void setSatOnTheBench(boolean satOnTheBench) {
        this.satOnTheBench = satOnTheBench;
    }

    public List<Person> getMyDateList() {
        return myDateList;
    }
    public void setMyDateList(List<Person> myDateList) {
        this.myDateList = myDateList;
    }

    public void addToMyDateList(Person person) {
        myDateList.add(person);
    }
}
