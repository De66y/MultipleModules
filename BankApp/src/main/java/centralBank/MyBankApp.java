package centralBank;

import java.util.ArrayList;
import java.util.Scanner;

public class MyBankApp {

    public static void main(String[] args) {
        ATM ATM = new ATM();
        ATM.mainMenu(new Scanner(System.in), new Bank(new ArrayList<>(), new BackOffice()));
    }
}
