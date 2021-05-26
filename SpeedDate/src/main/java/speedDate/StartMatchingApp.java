package speedDate;//check of de person niet jezelf is
//printen aanpassen
//benchperson in de lijst voegen.

import speedDate.logic.MatchMaker2;
import speedDate.model.Person;

import java.util.ArrayList;
import java.util.List;

public class StartMatchingApp {

    public static void main(String[] args) {
        /*new speedDate.logic.MatchMaker(new speedDate.console.Datebooth().register()).wholeMatchProcess();

        List<Person> allPersonList;
        Datebooth datebooth = new Datebooth();
        allPersonList = datebooth.register();
        MatchMaker matchMaker = new MatchMaker(allPersonList);
        matchMaker.wholeMatchProcess();*/

        List<Person> testLijst = new ArrayList<>();
        testLijst.add(new Person("Beer"));
        testLijst.add(new Person("Jane"));
        testLijst.add(new Person("Dotje"));
        testLijst.add(new Person("Ruby"));
        //testLijst.add(new Person("Calimero"));

        System.out.println(new MatchMaker2().numberOfDateRounds(testLijst));

    }
}
