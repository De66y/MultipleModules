package centralBank;

import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {

    public void mainMenu(Scanner scanner, Bank bank) {
        int choice;
        boolean whileMenu = true;

        do {
            System.out.println("Welkom bij " + bank.getName() + " , waarmee kan ik u van dienst zijn:\n" +
                    "1. Een bankrekening aanmaken.\n" +
                    "2. Contant geld storten.\n" +
                    "3. Contant geld opnemen.\n" +
                    "4. Geld overmaken tussen bankrekeningen.\n" +
                    "5. Totaalbedrag van een bankrekening opvragen.\n" +
                    "6. Informatie van alle rekeningen.\n" +
                    "7. Afsluiten."
            );
            System.out.print("Keuze: ");

            choice = scanner.nextInt();

            if (choice == 1) {
                //1. Een bankrekening aanmaken.
                BankAccount bankAccount = new BankAccount(bank.getCountAccountNumber());
                bank.setCountAccountNumber(bank.getCountAccountNumber() + 1);
                System.out.println("Accountnumber: " + bankAccount.getAccountNumber());
                bank.setBank(bankAccount);
                System.out.println("Bank getallaccountlist: " + bank.getBank().size());
            } else if (choice == 2) {
                //2. Contant geld storten
                System.out.print("Naar welke bankrekening wilt u contant geld storten?\n" +
                        "Vul het bankrekeningnummer in: ");
                int accountNumber = scanner.nextInt();
                System.out.print("Hoeveel contant geld wil je storen: ");
                BigDecimal money = scanner.nextBigDecimal();
                bank.getBackOffice().depositMoney(money, bank.getBank().get(accountNumber));
            } else if (choice == 3) {
                //3. Contant geld opnemen.
                System.out.print("Van welke bankrekening wilt u contant geld opnemen?\n" +
                        "Vul het bankrekeningnummer in: ");
                int accountNumber = scanner.nextInt();
                System.out.print("Hoeveel geld wilt u opnemen: ");
                BigDecimal money = scanner.nextBigDecimal();
                bank.getBackOffice().withdrawMoney(money, bank.getBank().get(accountNumber));
            } else if (choice == 4) {
                //4. Geld overmaken tussen bankrekeningen. XXXXX
                System.out.print("Van welke rekening wilt u het geld overmaken: ");
                int vanRekening = scanner.nextInt();
                System.out.print("Naar welke rekening wilt u het geld overmaken: ");
                int naarRekening = scanner.nextInt();
                System.out.print("Hoeveel geld wilt u overmaken: ");
                BigDecimal money = scanner.nextBigDecimal();
                bank.getBackOffice().withdrawMoney(money, bank.getBank().get(vanRekening));
                bank.getBackOffice().depositMoney(money, bank.getBank().get(naarRekening));
            } else if (choice == 5) {
                //5. Totaalbedrag van een bankrekening opvragen.
                System.out.print("Van welke rekeningnummer wilt u het totaalbedrag weten: ");
                int accountNumber = scanner.nextInt();
                System.out.println("Het totaalbedrag is: " + bank.getBank().get(accountNumber).getBalance());
            } else if (choice == 6) {
                //6. Informatie van alle rekeningen.
                bank.printAllBankRecords();
            } else if (choice == 7) {
                //7. Afsluiten.
                System.out.println("Tot de volgende keer.");
                whileMenu = false;
            } else {
                System.out.println("Verkeerde invoer, kies opnieuw");
            }

        } while (whileMenu);
    }
}

