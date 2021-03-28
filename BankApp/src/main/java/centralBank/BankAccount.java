package centralBank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {
    private final BigDecimal interestPercentage = BigDecimal.valueOf(0.1); //Rente is 10%
    private final int accountNumber;
    private BigDecimal balance;
    private BigDecimal interestRate;

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.valueOf(0);
        this.interestRate = BigDecimal.valueOf(0);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getInterestRate() {
        this.interestRate = this.balance.multiply(interestPercentage).setScale(2, RoundingMode.HALF_UP);
        return this.interestRate;
    }
}

