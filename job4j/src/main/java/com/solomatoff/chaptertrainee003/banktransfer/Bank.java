package com.solomatoff.chaptertrainee003.banktransfer;

import java.util.ArrayList;
import java.util.List;

import java.util.TreeMap;

/**
 *  Класс для управления банковскими переводами
 */
public class Bank {

    private TreeMap<User, ArrayList<Account>> treemap = new TreeMap<>();

    /**
     * Метод возвращает текущую карту для использования.
     * @return this.treemap.
     */
    public TreeMap<User, ArrayList<Account>> getTreemap() {
        return this.treemap;
    }

    /**
     * Метод добавляет пользователя в нашу карту.
     * @param user новый пользователь.
     */
    public void addUser(User user) {
        this.treemap.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя из нашей карты.
     * @param user удаляемый пользователь.
     */
    public void deleteUser(User user) {
        this.treemap.remove(user);
    }

    /**
     * Метод добавляет новый счет указанному пользователю.
     * @param user пользователь.
     * @param account новый счет.
     */
    public void addAccountToUser(User user, Account account) {
        this.treemap.get(user).add(account);
    }

    /**
     * Метод возвращает конкретный счет указанного пользователя.
     * @param user пользователь.
     * @param account новый счет.
     * @return счет пользователя.
     */
    private Account getActualAccount(User user, Account account) {
        ArrayList<Account> list = this.treemap.get(user);
        return list.get(list.indexOf(account));
    }

    /**
     * Метод удаляет указанный счет указанного пользователя.
     * @param user пользователь.
     * @param account новый счет.
     */
    public void deleteAccountFromUser(User user, Account account) {
        this.treemap.get(user).remove(account);
    }

    /**
     * Метод возвращает список счетов указанного пользователя.
     * @param user пользователь.
     * @return список счетов пользователя.
     */
    public List<Account> getUserAccounts(User user) {
        return this.treemap.get(user);
    }

    /**
     * Метод переводит деньги с указанного счета пользователя на конкретный счет другого пользователя.
     * @param user1 первый пользователь.
     * @param account1 счет первого пользователя.
     * @param user2 второй пользователь.
     * @param account2 счет второго пользователя.
     * @return если счёт не найден или не хватает денег на счёте (с которого переводятся деньги) должен вернуть false, в случае успеха - true.
     */
    public boolean transferMoney(User user1, Account account1,
                            User user2, Account account2, double amount) {
        return this.treemap.get(user1).contains(account1)
                && this.treemap.get(user2).contains(account2)
                && getActualAccount(user1, account1).transfer(
                getActualAccount(user2, account2), amount);
    }

}
