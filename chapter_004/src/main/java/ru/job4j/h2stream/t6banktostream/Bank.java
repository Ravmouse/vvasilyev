package ru.job4j.h2stream.t6banktostream;

import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 07.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Bank {
    /**
     * @param user клиент банка.
     */
    void addUser(User user);

    /**
     * @param user клиент банка.
     */
    void deleteUser(User user);

    /**
     * @param passport паспорт клиента.
     * @param account банковский счет клиента.
     */
    void addAccount(String passport, Account account);

    /**
     * @param passport паспорт клиента.
     * @param account банковский счет клиента.
     */
    void deleteAccount(String passport, Account account);

    /**
     * @param passport паспорт клиента.
     * @return список всех банковских счетов клиента банка по данному паспорту.
     */
    List<Account> getAccounts(String passport);

    /**
     * @param fromPas паспорт клиента банка, от которого идет перевод.
     * @param fromReq реквизиты банковского счета клиента, от которого идет перевод.
     * @param toPas паспорт клиента банка, которому направляется перевод.
     * @param toReq реквизиты банковского счета клиента, которому направляется перевод.
     * @param amount сумма.
     * @return true, если перевод был успешно осуществлен, и false, если - нет.
     */
    boolean transfer(String fromPas, String fromReq, String toPas, String toReq, double amount);
}