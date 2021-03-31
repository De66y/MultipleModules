package zelfPrutsen.logger;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.print();

        Printer printer = new Printer();
        printer.printsomeLogs();
    }
}
