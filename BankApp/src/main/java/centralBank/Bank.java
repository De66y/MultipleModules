package centralBank;

import java.util.List;

public class Bank {
    private final String name = "DebBank";
    private final List<BankAccount> bank;
    private int countAccountNumber;
    private final BackOffice backOffice;

    public Bank(List<BankAccount> bank, BackOffice backOffice) {
        this.bank = bank;
        this.backOffice = backOffice;
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getBank() {
        return bank;
    }

    public void setBank(BankAccount bankAccount) {
        this.bank.add(bankAccount);
    } //Aangepast

    public int getCountAccountNumber() {
        return countAccountNumber;
    }

    public void setCountAccountNumber(int countAccountNumber) {
        this.countAccountNumber = countAccountNumber;
    }

    public BackOffice getBackOffice() {
        return backOffice;
    }

    public void printAllBankRecords() {
        for (BankAccount bankAccount : this.getBank()) {
            System.out.println("Accountnummer: " + bankAccount.getAccountNumber() + " || " +
                    "Totaalbedrag op de rekening: " + bankAccount.getBalance() + " || " +
                    "Rente op dit moment voor één jaar: " + bankAccount.getInterestRate());
        }
    }
}

