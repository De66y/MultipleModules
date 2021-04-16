package speedDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Datebooth {

    public List<Person> register() {
        List<Person> allPersonsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many persons are going the speeddate ");
        System.out.print("Enter a number: ");
        int amountOfPersons = scanner.nextInt();

        for (int i = 0; i < amountOfPersons; i++) {
            scanner = new Scanner(System.in);
            System.out.printf("Enter the name of person %d .Don't use duplicate names. ", (i + 1));
            String name = scanner.nextLine();

            Person person = new Person(name);

            allPersonsList.add(person);
        }
        return allPersonsList;
    }
}
