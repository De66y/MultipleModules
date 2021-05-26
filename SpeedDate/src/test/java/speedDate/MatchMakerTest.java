package speedDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import speedDate.logic.MatchMaker;
import speedDate.model.Match;
import speedDate.model.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchMakerTest {
    @InjectMocks
    MatchMaker matchmaker;

    List<Person> allPersonList = new ArrayList<>();
    private List<Match> temporaryMatchedList = new ArrayList<>();
    private List<Person> temporaryMatchedPersonList = new ArrayList<>();

    Person vincent;
    Person debby;
    Person jamie;
    Person dotje;


    @Mock
    Match match = new Match(vincent, debby);

    @BeforeEach
    void setUp() {
        vincent = new Person("Vincent");   //Gematched
        debby = new Person("Debby");       //Gematched
        jamie = new Person("Jamie");       //Temporary bench person
        dotje = new Person("Dotje");       //Los

        allPersonList.add(vincent);
        allPersonList.add(debby);
        allPersonList.add(jamie);
        allPersonList.add(dotje);
        allPersonList.add(new Person("Beer"));

        matchmaker = new MatchMaker(allPersonList);

        temporaryMatchedList.add(match);
    }

    @Test
    public void goodAmountOfDateRoundsUnevenTest() {
        //Given

        //When
        int actual = matchmaker.dateRounds();

        //Then
        assertEquals(5, actual);
    }

    @Test
    public void goodAmountOfDateRoundsEvenTest() {
        //Given
        allPersonList.add(new Person("Ruby"));

        //When
        int actual = matchmaker.dateRounds();

        //Then
        assertEquals(5, actual);
    }

    //@TODO
    @Test
    public void putPersonOnTemporaryBench() {
        //Given

        //When
        matchmaker.putPersonOnTemporaryBench(jamie);
        int actual = temporaryMatchedPersonList.size();

        //Then
        assertEquals(jamie, matchmaker.getTemporaryBenchPerson());
        assertEquals(1, actual);
    }

    @Test
    public void temporaryMatchedListEmptySoPersonIsntMatched() {
        //Given

        //When
        boolean boo3 = matchmaker.alreadyMatched(dotje);

        //Then
        assertEquals(false, boo3);
    }

    @Test
    public void sizeTemporaryMatchedPersonListIsEmptySoIs0() {
        //Given

        //When
        int actual = temporaryMatchedPersonList.size();

        //Then
        assertEquals(0, actual);
    }

    @Test
    public void personAlreadyMatched() {
        //Given
        temporaryMatchedPersonList.add(vincent);
        temporaryMatchedPersonList.add(debby);
        matchmaker.setTemporaryMatchedPersonList(temporaryMatchedPersonList);

        //When
        int sizeList = temporaryMatchedPersonList.size();

        boolean boo = matchmaker.alreadyMatched(vincent);
        boolean boo2 = matchmaker.alreadyMatched(debby);

        //Then
        assertEquals(2, sizeList);

        assertEquals(true, boo);
        assertEquals(true, boo2);
    }

    @Test
    public void personNotAlreadyMatched() {
        //Given
        temporaryMatchedPersonList.add(vincent);
        temporaryMatchedPersonList.add(debby);

        //When
        int sizeList = temporaryMatchedPersonList.size();
        boolean boo3 = matchmaker.alreadyMatched(dotje);


        //Then
        assertEquals(2, sizeList);
        assertEquals(false, boo3);
    }

    @Test
    public void sizeAllPersonList() {
        //Given

        //When
        int actual = allPersonList.size();

        //Then
        assertEquals(5, actual);
    }

    @Test
    public void sizeTemporaryMatchedList() {
        //Given

        //When
        int actual = temporaryMatchedList.size();

        //Then
        assertEquals(1, actual);
    }
}