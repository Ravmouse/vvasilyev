package ru.job4j.testtaskbankmap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank class.
 */
public class Bank {

    /**
     * The variable that is referenced to object implementing Map interface.
     */
    private Map<User, List<Account>> bankMap;

    /**
     * The constructor.
     */
    public Bank() {
        bankMap = new HashMap<>();
    }

    /**
     * @return the bankMap var.
     */
    public Map<User, List<Account>> getBankMap() {
        return bankMap;
    }

    /**
     * @param user to be added into the bank.
     */
    public void addUser(User user) {
        if (user != null) {
            List<Account> accountList = new ArrayList<>();
            accountList.add(new Account());
            bankMap.put(user, accountList);
        }
    }

    /**
     * @param user to be deleted from the bank.
     */
    public void deleteUser(User user) {
        if (user != null) {
            bankMap.remove(user, bankMap.get(user)); // Добавлено в jdk8 - удаление пары "ключ-значение"
        }                                       // Ключ - user, знач. получается при вызове метода get() по ключу user
    }

    /**
     * @param user with the list of his accounts.
     * @param account to be added to the user's list of accounts.
     */
    public void addAccountToUser(User user, Account account) {
        if ((user != null) && (account != null)) {
            List<Account> list = bankMap.get(user);
            list.add(account);
        }
    }

    /**
     * @param user with the list of his accounts.
     * @param account to be deleted from the user's list of accounts.
     */
    public void deleteAccountFromUser(User user, Account account) {
        if ((user != null) && (account != null)) {
            List<Account> list = bankMap.get(user);
            if (list.contains(account)) {
                list.remove(account);
            }
        }
    }

    /**
     * @param user with the list of his accounts.
     * @return the list of the user's accounts.
     */
    public List<Account> getUserAccounts(User user) {
        if (user != null) {
            return bankMap.get(user);
        } else {
            return null;
        }
    }

    /**
     * @param srcUser from who's account we need to take the money.
     * @param srcAccount to be taken the money from.
     * @param dstUser to who's account we need to put the money.
     * @param dstAccount to be put the money to.
     * @param amount of money.
     * @return true or false.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result;
        final List<Account> list = bankMap.get(dstUser);
        if ((list.contains(dstAccount)) && (srcAccount.checkValue(amount))) {
            srcAccount.withdraw(amount);
            dstAccount.deposit(amount);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * @param srcUser from who's account we need to take the money.
     * @param srcReq number of requisites we want to withdraw money from.
     * @param dstUser to who's account we need to put the money.
     * @param dstReq number of requisites we want to deposit money to.
     * @param amount of money.
     * @return true or false.
     */
    public boolean transferMoney(User srcUser, int srcReq, User dstUser, int dstReq, double amount) {
        boolean result = false;
        final List<Account> srcList = bankMap.get(srcUser);
        final List<Account> dstList = bankMap.get(dstUser);
        for (Account srcAcc : srcList) {
            if (srcAcc.getRequisites() == srcReq) {
                for (Account dstAcc : dstList) {
                    if ((dstAcc.getRequisites() == dstReq) && (srcAcc.checkValue(amount))) {
                        srcAcc.withdraw(amount);
                        dstAcc.deposit(amount);
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}