package ru.job4j.testtaskbankmap;
import org.junit.Test;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BankTest class.
 */
public class BankTest {
    /**
     * Adding User.
     */
    @Test
    public void whenAddUserThenBankHasTheSameUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Jack", 430412345));
        assertThat(bank.getBankMap().keySet(), contains(new User("Jack", 430412345)));
    }

    /**
     * Deleting User.
     */
    @Test
    public void whenDeleteUserThenBankDoesNotHaveThisUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Jack", 430412345));
        bank.addUser(new User("Dan", 430819763));
        bank.addUser(new User("Joe", 430125294));
        bank.deleteUser(new User("Joe", 430125294));
        bank.deleteUser(new User("Jack", 430412345));
        assertThat(bank.getBankMap().keySet(), contains(new User("Dan", 430819763)));
    }

    /**
     * Adding Account to the User.
     */
    @Test
    public void whenAddAccountToUserThenUserHasTheAccount() {
        Bank bank = new Bank();
        User user = new User("Jack", 430412345);
        bank.addUser(user);
        Account acc = new Account(123.45, 3213);
        bank.addAccountToUser(user, acc);
        assertThat(bank.getUserAccounts(user).contains(acc), is(true));
    }

    /**
     * Deleting Account from the User.
     */
    @Test
    public void whenDeleteAccountFromUserThenUserDoesNotHaveTheAccount() {
        Bank bank = new Bank();
        User user = new User("Jack", 430412345);
        bank.addUser(user);
        Account acc1 = new Account(123.45, 3213);
        Account acc2 = new Account(456.45, 1332);
        Account acc3 = new Account(789.45, 7845);
        bank.addAccountToUser(user, acc1);
        bank.addAccountToUser(user, acc2);
        bank.addAccountToUser(user, acc3);
        bank.deleteAccountFromUser(user, bank.getUserAccounts(user).get(0));
        bank.deleteAccountFromUser(user, acc1);
        bank.deleteAccountFromUser(user, acc3);
        assertThat(bank.getUserAccounts(user), contains(acc2));
    }

    /**
     * Getting the User's list of accounts.
     */
    @Test
    public void whenGetUserAccountsThenGetThemAllAsTheList() {
        Bank bank = new Bank();
        User user = new User("Jack", 430412345);
        bank.addUser(user);
        Account acc1 = new Account(123.45, 3213);
        Account acc2 = new Account(456.45, 1332);
        Account acc3 = new Account(789.45, 7845);
        bank.addAccountToUser(user, acc1);
        bank.addAccountToUser(user, acc2);
        bank.addAccountToUser(user, acc3);
        assertThat(bank.getUserAccounts(user), contains(bank.getUserAccounts(user).get(0), acc1, acc2, acc3));
    }

    /**
     * Transferring the money from one User to another.
     */
    @Test
    public void whenTransferMoneyThenMoneyIsTransferredFromOneAccountToAnother() {
        Bank bank = new Bank();
        User user1 = new User("Jack", 430412345);
        User user2 = new User("Joe", 430511875);
        bank.addUser(user1);
        bank.addUser(user2);
        Account acc1 = new Account(12300.45, 3213);
        Account acc2 = new Account(456.45, 1332);

        bank.addAccountToUser(user1, acc1);
        bank.addAccountToUser(user2, acc2);
        bank.transferMoney(user1, acc1, user2, acc2, 10000);

        Account testAcc = new Account(12300.45, 3213);
        testAcc.withdraw(10000);
        assertThat(bank.getUserAccounts(user1).get(1).getValue(), is(testAcc.getValue()));

        testAcc = new Account(456.45, 1332);
        testAcc.deposit(10000);
        assertThat(bank.getUserAccounts(user2).get(1).getValue(), is(testAcc.getValue()));
    }
}