package centralBank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {

    @Test
    void getInterestRate() {
        //given
        //Given
        BankAccount bankAccount = new BankAccount(5);

        //When
        int actual = bankAccount.getAccountNumber();

        //Then
        assertEquals(5, actual);
        //when

        //then
    }
}