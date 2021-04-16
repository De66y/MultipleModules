package speedDate;//check of de person niet jezelf is
//printen aanpassen
//benchperson in de lijst voegen.

import java.util.List;

public class StartMatchingApp {

    public static void main(String[] args) {
        //new speedDate.MatchMaker(new speedDate.Datebooth().register()).wholeMatchProcess();

        List<Person> allPersonList;
        Datebooth datebooth = new Datebooth();
        allPersonList = datebooth.register();
        MatchMaker matchMaker = new MatchMaker(allPersonList);
        matchMaker.wholeMatchProcess();
    }
}
