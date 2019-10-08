package ru.job4j.h2stream.t6banktostream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 07.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class BankImpl implements Bank {
    /**
     * Отображение.
     */
    private final Map<User, List<Account>> bank = new HashMap<>();

    /**
     * @param user клиент банка.
     */
    @Override
    public void addUser(final User user) {
        Optional.ofNullable(user).ifPresent(u -> {
            final List<Account> accounts = new ArrayList<>();
            accounts.add(new Account(1000D));
            bank.putIfAbsent(user, accounts);
        });
    }

    /**
     * @param user клиент банка.
     * @param account  банковский счет клиента.
     */
    public void addUser(final User user, final Account account) {
        Optional.ofNullable(user).ifPresent(u -> {
            final List<Account> accounts = new ArrayList<>();
            accounts.add(Optional.ofNullable(account).orElse(new Account()));
            bank.putIfAbsent(user, accounts);
        });
    }

    /**
     * @param user клиент банка.
     */
    @Override
    public void deleteUser(final User user) {
        Optional.ofNullable(user).ifPresent(us -> bank.remove(user));
    }

    /**
     * @param passport паспорт клиента.
     * @param account  банковский счет клиента.
     */
    @Override
    public void addAccount(final String passport, final Account account) {
        final Optional<String> pas = Optional.ofNullable(passport);
        final Optional<Account> acc = Optional.ofNullable(account);
        bank.keySet()
                .stream()
                .filter(u -> pas.orElse("").equals(u.getPassport()))
                .forEach(u -> bank.get(u).add(acc.orElse(new Account())));
    }

    /**
     * @param passport паспорт клиента.
     * @param account  банковский счет клиента.
     */
    @Override
    public void deleteAccount(final String passport, final Account account) {
        final Optional<String> pas = Optional.ofNullable(passport);
        final Optional<Account> acc = Optional.ofNullable(account);
        bank.keySet()
                .stream()
                .filter(u -> pas.orElse("").equals(u.getPassport()))
                .forEach(u -> {
                    final List<Account> accounts = bank.get(u)
                            .stream()
                            .filter(a -> !acc.orElse(new Account()).equals(a))
                            .collect(Collectors.toList());
                    bank.put(u, accounts);
                });
    }

    /**
     * @param passport паспорт клиента.
     * @return список всех банковских счетов клиента банка по данному паспорту.
     */
    @Override
    public List<Account> getAccounts(final String passport) {
        final Optional<String> pas = Optional.ofNullable(passport);
        return bank
                 .keySet()
                 .stream()
                 .filter(u -> pas.orElse("").equals(u.getPassport()))
                 .flatMap(u -> bank.get(u).stream())
                 .collect(Collectors.toList());
    }

    /**
     * @return список клиентов банка.
     */
    public List<User> getUsers() {
        return new ArrayList<>(bank.keySet());
    }

    /**
     * @param fromPas паспорт клиента банка, от которого идет перевод.
     * @param fromReq реквизиты банковского счета клиента, от которого идет перевод.
     * @param toPas   паспорт клиента банка, которому направляется перевод.
     * @param toReq   реквизиты банковского счета клиента, которому направляется перевод.
     * @param amount  сумма.
     * @return true, если перевод был успешно осуществлен, и false, если - нет.
     */
    @Override
    public boolean transfer(String fromPas, String fromReq, String toPas, String toReq, double amount) {
        boolean result = false;
        if (accountExists(fromPas, fromReq) && accountExists(toPas, toReq)) {
            final Account account = getAccount(fromPas, fromReq);
            if (account.isEnoughMoney(amount)) {
                account.withdraw(amount);
                getAccount(toPas, toReq).deposit(amount);
                result = true;
            }
        }
        return result;
    }

    /**
     * @param passport паспорт клиента.
     * @param requisites реквизиты банковского счета клиента.
     * @return true, если счет по таким реквизитам есть, и false, если - нет.
     */
    private boolean accountExists(String passport, String requisites) {
        return bank
                .keySet()
                .stream()
                .filter(user -> Optional.ofNullable(passport).orElse("").equals(user.getPassport()))
                .flatMap(user -> bank.get(user).stream())
                .anyMatch(account -> Optional.ofNullable(requisites).orElse("").equals(account.getRequisites()));
    }

    /**
     * @param passport паспорт клиента.
     * @param requisites реквизиты банковского счета клиента.
     * @return банковский счет клиента по таким реквизитам.
     */
    private Account getAccount(String passport, String requisites) {
        return bank
                .keySet()
                .stream()
                .filter(user -> Optional.ofNullable(passport).orElse("").equals(user.getPassport()))
                .flatMap(user -> bank.get(user).stream())
                .filter(account -> Optional.ofNullable(requisites).orElse("").equals(account.getRequisites()))
                .findFirst().get();
    }
}