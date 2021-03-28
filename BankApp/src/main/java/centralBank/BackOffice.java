package centralBank;

import java.math.BigDecimal;

public class BackOffice {
    public void depositMoney(BigDecimal amount, BankAccount bankAccount) {
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
    }

    public void withdrawMoney(BigDecimal amount, BankAccount bankAccount) {
        if (bankAccount.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) { //-1 als de som kleiner is dan zero
            throw new NotEnoughMoneyException();
        } else {
            bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        }
    }

}

