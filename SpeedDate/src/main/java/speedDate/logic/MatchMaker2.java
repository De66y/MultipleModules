package speedDate.logic;

import speedDate.model.Person;

import java.util.List;

public class MatchMaker2 {
    public int numberOfDateRounds(List<Person> list) {
        if (unevenContesters(list)) return list.size();

        return list.size()-1;
    }
    public boolean unevenContesters(List<Person> list) {
        if (list.size()%2 == 0) return false;
        return true;

    }


}
