package speedDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchMaker {
    private final List<Person> allPersonsList;
    private List<Match> temporaryMatchedList;
    private List<Person> temporaryMatchedPersonList;

    private Person temporaryBenchPerson;

    private int personsMatchedCounter;
    private int matchedCounter;
    private int personBenchCounter;

    private final Random randomgenerator;

    public MatchMaker(List<Person> allPersonsList) {
        this.temporaryMatchedList = new ArrayList<>();
        this.temporaryMatchedPersonList = new ArrayList<>();
        this.allPersonsList = allPersonsList;

        this.temporaryBenchPerson = null;

        this.personsMatchedCounter = 0;
        this.matchedCounter = 0;
        this.personBenchCounter = 0;

        this.randomgenerator = new Random();
    }

    public Person getTemporaryBenchPerson() {
        return temporaryBenchPerson;
    } //Gemaakt voor tests

    public void setTemporaryMatchedPersonList(List<Person> temporaryMatchedPersonList) {
        this.temporaryMatchedPersonList = temporaryMatchedPersonList;
    } //Gemaakt voor tests

    public void wholeMatchProcess() {
        int dateRounds = dateRounds();

        for(int i = 0; i<dateRounds; i++) {

            putPersonOnTemporaryBench(allPersonsList.get(personBenchCounter));

            for (Person person : allPersonsList) {
                if (checksAllForPersonPositive(person)) {
                    Person theMatch = checkRandomPerson(person);

                    if (theMatch == null) startOverDateRound();
                    else lockTemporaryMatch(person, theMatch);
                } //else overslaan en naar de volgende persoon
            } //Einde for loop

            lockAllMatches();
        }

        printAllDateLists();
    }

    public int dateRounds() {
        if (unevenContesters()) return allPersonsList.size();

        return allPersonsList.size()-1;
    }
    public boolean unevenContesters() {
        if (allPersonsList.size()%2 == 0) return false;
        return true;

    }
    public void putPersonOnTemporaryBench(Person person) {
        temporaryBenchPerson = person;
        temporaryMatchedPersonList.add(person);
    }
    public void putPersonOnBench(Person person){
        person.addToMyDateList(new Person("On the bench"));
    }
    public Person checkRandomPerson(Person person) {
        int index = randomgenerator.nextInt(allPersonsList.size());
        Person randomPerson = allPersonsList.get(index);

        if (randomPerson.getNaam().equals(person.getNaam()) || randomPerson.getNaam().equals(temporaryBenchPerson.getNaam())) checkRandomPerson(person);

        if (checksAllForPersonPositive(randomPerson)) {
            if (lastMatch() && alreadyDatetedEachOther(person, randomPerson)) randomPerson = null;
            if (alreadyDatetedEachOther(person, randomPerson)) checkRandomPerson(person);
        }
        return randomPerson;
    }
    public boolean alreadyMatched(Person person) {
        Boolean boo = false;
        for (Person persons : temporaryMatchedPersonList) {
            if (person.getNaam().equals(persons.getNaam())) boo = true; //Checkt op basis van de inhoud van de String
        }
        return boo;
    }
    public boolean checksAllForPersonPositive(Person person) {
        if (person.getNaam().equals(temporaryBenchPerson.getNaam()) || alreadyMatched(person)) {
            return false;
        }
        return true;
    }
    public boolean alreadyDatetedEachOther(Person person, Person randomPerson){
        Boolean boo = false;
        for (int i = 0; i<person.getMyDateList().size(); i++) {
            if (person.getMyDateList().get(i).getNaam().equals(person.getNaam())) boo = true;
        }
        return boo;
    }
    public boolean lastMatch() {
        if (unevenContesters()) {
            if (((allPersonsList.size()-3)/2) == matchedCounter) return true;
        } else {
            if (((allPersonsList.size()-2)/2) == matchedCounter) return true;
        }
        return false;
    }
    public void lockTemporaryMatch(Person person, Person theMatch) {
        temporaryMatchedList.add(new Match(person, theMatch));
        temporaryMatchedPersonList.add(person);
        temporaryMatchedPersonList.add(theMatch);
        personsMatchedCounter = personsMatchedCounter + 2;
    }
    public void lockAllMatches() {
        if(unevenContesters()) {
            putPersonOnBench(allPersonsList.get(personBenchCounter));
            personBenchCounter++;
        }

        for (Match match : temporaryMatchedList) {
            match.getPerson1().addToMyDateList(match.getPerson2());
            match.getPerson2().addToMyDateList(match.getPerson1());

            allPersonsList.get(matchedCounter).setSatOnTheBench(true);
            matchedCounter++;
        }

        startOverDateRound();
    }
    public void startOverDateRound() {
        temporaryMatchedList = new ArrayList<>();
        temporaryMatchedPersonList = new ArrayList<>();
        temporaryBenchPerson = new Person();
        personsMatchedCounter = 0;
        matchedCounter = 0;

    }
    public void printAllDateLists(){
        for (Person person: allPersonsList) {
            System.out.println("Datelist of: " + person.getNaam());
            int counterRounds = 0;
            for (int i = 0; i<person.getMyDateList().size(); i++) {
                System.out.printf("Round %d : %s\n", counterRounds+1, person.getMyDateList().get(i).getNaam());
                counterRounds ++;
            }
        }
    }

}
