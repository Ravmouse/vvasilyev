package ru.job4j.h2stream.t6banktostream;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 07.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class BankImplTest {
    /**
     * Добавление юзера.
     */
    @Test
    public void addUserTest() {
        User user1 = new User("Mike", "123");
        BankImpl b1 = new BankImpl();
        b1.addUser(user1);
        assertThat(b1.getUsers(), is(Collections.singletonList(user1)));
    }

    /**
     * Удаление юзера из одного банка и после этого сравнение двух банков.
     */
    @Test
    public void deleteUserTest() {
        User user1 = new User("Mike", "123");
        Account acc1 = new Account(1D);

        User user2 = new User("Paul", "456");
        Account acc2 = new Account(2D);

        BankImpl b1 = new BankImpl();
        b1.addUser(user1, acc1);
        b1.addUser(user2, acc2);

        BankImpl b2 = new BankImpl();
        b2.addUser(user2, acc2);

        b1.deleteUser(user1);
        assertThat(b1.getUsers(), is(b2.getUsers()));
    }

    /**
     * Добавление юзеру по одному счету и проверка, что у юзера количество счетов увеличивается.
     */
    @Test
    public void addAccountTest() {
        Account acc1 = new Account(1D);
        Account acc2 = new Account(2D);
        Account acc3 = new Account(3D);
        BankImpl b = new BankImpl();
        b.addUser(new User("Mike", "123"), acc1);
        assertThat(b.getAccounts("123"), is(Collections.singletonList(acc1)));

        b.addAccount("123", acc2);
        assertThat(b.getAccounts("123"), is(Arrays.asList(acc1, acc2)));

        b.addAccount("123", acc3);
        assertThat(b.getAccounts("123"), is(Arrays.asList(acc1, acc2, acc3)));
    }

    /**
     * Добавление и удаление счета у юзера.
     */
    @Test
    public void deleteAccountTest() {
        Account acc1 = new Account(1D);
        Account acc2 = new Account(2D);
        Account acc3 = new Account(3D);
        BankImpl b = new BankImpl();
        b.addUser(new User("Mike", "123"), acc1);
        b.addAccount("123", acc2);
        b.addAccount("123", acc3);
        b.deleteAccount("123", acc1);
        assertThat(b.getAccounts("123"), is(Arrays.asList(acc2, acc3)));
    }

    /**
     * Получение всех счетов у юзера, который имеется в банке.
     */
    @Test
    public void getAccountsTestWithCorrectValue() {
        Account acc1 = new Account(1D);
        Account acc2 = new Account(2D);
        Account acc3 = new Account(3D);
        BankImpl b = new BankImpl();
        b.addUser(new User("Mike", "123"), acc1);
        b.addAccount("123", acc2);
        b.addAccount("123", acc3);
        assertThat(b.getAccounts("123"), is(Arrays.asList(acc1, acc2, acc3)));
    }

    /**
     * Получение всех счетов у юзера, которого в банке нет.
     */
    @Test
    public void getAccountsTestWithWrongValue() {
        Account acc1 = new Account(1D);
        Account acc2 = new Account(2D);
        Account acc3 = new Account(3D);
        BankImpl b = new BankImpl();
        b.addUser(new User("Mike", "123"), acc1);
        b.addAccount("123", acc2);
        b.addAccount("123", acc3);
        assertThat(b.getAccounts("456"), empty());
    }

    /**
     * Если вместо паспорта юзера передается null.
     */
    @Test
    public void getAccountsTestWithNull() {
        Account acc1 = new Account(1D);
        Account acc2 = new Account(2D);
        Account acc3 = new Account(3D);
        BankImpl b = new BankImpl();
        b.addUser(new User("Mike", "123"), acc1);
        b.addAccount("123", acc2);
        b.addAccount("123", acc3);
        assertThat(b.getAccounts(null), empty());
    }

    /**
     * Проверка трансфера.
     */
    @Test
    public void transferTest() {
        User user1 = new User("Mike", "123");
        Account acc1 = new Account(1000D);

        User user2 = new User("Paul", "456");
        Account acc2 = new Account(1000D);

        BankImpl b1 = new BankImpl();
        b1.addUser(user1, acc1);
        b1.addUser(user2, acc2);

        boolean result = b1.transfer("123", acc1.getRequisites(), "456", acc2.getRequisites(), 500D);

        assertThat(result, is(true));
        assertThat(acc1.getValue(), is(500D));
        assertThat(acc2.getValue(), is(1500D));
    }
}