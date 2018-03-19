package ru.job4j.banktransfer;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenAddUserAndGetTreeMap() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        User usertwo = new User("Людмила", "6502 261214");
        bank.addUser(usertwo);

        TreeMap<User, ArrayList<Account>> expected = new TreeMap<>();
        expected.put(user, new ArrayList<>());
        expected.put(usertwo, new ArrayList<>());
        //System.out.println(expected);

        TreeMap<User, ArrayList<Account>> result = bank.getTreemap();
        //System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenAddUserDouble() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        User usertwo = new User("Олег", "6502 261212");
        bank.addUser(usertwo);

        TreeMap<User, ArrayList<Account>> expected = new TreeMap<>();
        expected.put(user, new ArrayList<>());
        //System.out.println(expected);

        TreeMap<User, ArrayList<Account>> result = bank.getTreemap();
        //System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenBankDeleteUserThenWasPresent() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        User usertwo = new User("Людмила", "6502 261214");
        bank.addUser(usertwo);

        // удаляем первого User
        bank.deleteUser(user);

        TreeMap<User, ArrayList<Account>> expected = new TreeMap<>();
        expected.put(usertwo, new ArrayList<>());
        //System.out.println(expected);

        TreeMap<User, ArrayList<Account>> result = bank.getTreemap();
        //System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenBankDeleteUserThenWasAbsent() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        User usertwo = new User("Людмила", "6502 261214");
        //bank.addUser(usertwo);

        // пытаемся удалить пользователя, которого нет
        bank.deleteUser(usertwo);
        TreeMap<User, ArrayList<Account>> expected = new TreeMap<>();
        expected.put(user, new ArrayList<>());
        //System.out.println(expected);

        TreeMap<User, ArrayList<Account>> result = bank.getTreemap();
        //System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenBankaAddAccountToUser() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        Account accountone = new Account(1000d, "Счет №1");
        Account accounttwo = new Account(2000d, "Счет №2");
        bank.addAccountToUser(user, accountone);
        bank.addAccountToUser(user, accounttwo);

        List<Account> expected = Arrays.asList(new Account(1000d, "Счет №1"), new Account(2000d, "Счет №2"));

        List<Account> result = bank.getUserAccounts(user);
        assertThat(result, is(expected));
    }

    @Test
    public void whenBankDeleteAccountFromUserWasPresent() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        Account accountone = new Account(1000d, "Счет №1");
        Account accounttwo = new Account(2000d, "Счет №2");
        bank.addAccountToUser(user, accountone);
        bank.addAccountToUser(user, accounttwo);

        List<Account> expected = Collections.singletonList(new Account(2000d, "Счет №2"));

        // удаляем первый счет
        bank.deleteAccountFromUser(user, accountone);

        List<Account> result = bank.getUserAccounts(user);
        assertThat(result, is(expected));
    }

    @Test
    public void whenBankDeleteAccountFromUserWasAbsent() {
        Bank bank = new Bank();

        User user = new User("Олег", "6502 261212");
        bank.addUser(user);

        Account accountone = new Account(1000d, "Счет №1");
        Account accounttwo = new Account(2000d, "Счет №2");
        //bank.addAccountToUser(user, accountone);
        bank.addAccountToUser(user, accounttwo);

        List<Account> expected = Collections.singletonList(new Account(2000d, "Счет №2"));

        // удаляем первый счет
        bank.deleteAccountFromUser(user, accountone);

        List<Account> result = bank.getUserAccounts(user);
        assertThat(result, is(expected));
    }

    @Test
    public void whenBankGetUserAccounts() {
        Bank bank = new Bank();

        User userone = new User("Олег", "6502 261212");
        bank.addUser(userone);

        User usertwo = new User("Людмила", "6502 261214");
        bank.addUser(usertwo);

        Account accountoneone = new Account(1000d, "Счет №1");
        Account accountonetwo = new Account(2000d, "Счет №2");
        Account accounttwoone = new Account(15000d, "Счет №1");
        Account accounttwotwo = new Account(30000d, "Счет №2");

        bank.addAccountToUser(userone, accountoneone);
        bank.addAccountToUser(userone, accountonetwo);
        bank.addAccountToUser(usertwo, accounttwoone);
        bank.addAccountToUser(usertwo, accounttwotwo);

        List<Account> expectedone = Arrays.asList(new Account(1000d, "Счет №1"), new Account(2000d, "Счет №2"));
        List<Account> expectedtwo = Arrays.asList(new Account(15000d, "Счет №1"), new Account(30000d, "Счет №2"));
        //System.out.println(expectedone);
        //System.out.println(expectedtwo);

        // Проверяем список счетов первого User
        List<Account> resultone = bank.getUserAccounts(userone);
        //System.out.println(resultone);

        assertThat(resultone, is(expectedone));

        // Проверяем список счетов второго User
        List<Account> resulttwo = bank.getUserAccounts(usertwo);
        //System.out.println(resulttwo);

        assertThat(resulttwo, is(expectedtwo));
    }

    @Test
    public void whenBankTransferMoney() {
        Bank bank = new Bank();

        User userone = new User("Олег", "6502 261212");
        bank.addUser(userone);

        User usertwo = new User("Людмила", "6502 261214");
        bank.addUser(usertwo);

        Account accountone = new Account(1000d, "Счет №1");
        Account accounttwo = new Account(2000d, "Счет №1");

        bank.addAccountToUser(userone, accountone);
        bank.addAccountToUser(usertwo, accounttwo);

        List<Account> expectedone = Collections.singletonList(new Account(500d, "Счет №1"));
        List<Account> expectedtwo = Collections.singletonList(new Account(2500d, "Счет №1"));
        //System.out.println(expectedone);
        //System.out.println(expectedtwo);

        // Переводим деньги со счета на счет
        bank.transferMoney(userone, accountone, usertwo, accounttwo, 500d);

        // Проверяем список счетов первого User
        List<Account> resultone = bank.getUserAccounts(userone);
        //System.out.println(resultone);

        assertThat(resultone, is(expectedone));

        // Проверяем список счетов второго User
        List<Account> resulttwo = bank.getUserAccounts(usertwo);
        //System.out.println(resulttwo);

        assertThat(resulttwo, is(expectedtwo));
    }

    @Test
    public void whenBankTransferMoneyOneUser() {
        Bank bank = new Bank();

        User userone = new User("Олег", "6502 261212");
        bank.addUser(userone);

        Account accountone = new Account(1000d, "Счет №1");
        Account accounttwo = new Account(2000d, "Счет №2");

        bank.addAccountToUser(userone, accountone);
        bank.addAccountToUser(userone, accounttwo);

        List<Account> expected = Arrays.asList(new Account(500d, "Счет №1"), new Account(2500d, "Счет №2"));

        // Переводим деньги со счета на счет
        bank.transferMoney(userone, accountone, userone, accounttwo, 500d);

        // Проверяем список счетов User
        List<Account> resultone = bank.getUserAccounts(userone);
        //System.out.println(resultone);

        assertThat(resultone, is(expected));
    }

    @Test
    public void whenBankDontTransferMoney() {
        Bank bank = new Bank();

        User userone = new User("Олег", "6502 261212");
        bank.addUser(userone);

        User usertwo = new User("Людмила", "6502 261214");
        bank.addUser(usertwo);

        Account accountone = new Account(1000d, "Счет №1");
        Account accounttwo = new Account(2000d, "Счет №1");

        bank.addAccountToUser(userone, accountone);
        bank.addAccountToUser(usertwo, accounttwo);

        List<Account> expectedone = Collections.singletonList(new Account(1000d, "Счет №1"));
        List<Account> expectedtwo = Collections.singletonList(new Account(2000d, "Счет №1"));
        //System.out.println(expectedone);
        //System.out.println(expectedtwo);

        // Переводим деньги со счета на счет
        boolean result = bank.transferMoney(userone, accountone, usertwo, accounttwo, 1500d);
        assertThat(result, is(false));

        // Проверяем список счетов первого User
        List<Account> resultone = bank.getUserAccounts(userone);
        //System.out.println(resultone);

        assertThat(resultone, is(expectedone));

        // Проверяем список счетов второго User
        List<Account> resulttwo = bank.getUserAccounts(usertwo);
        //System.out.println(resulttwo);

        assertThat(resulttwo, is(expectedtwo));
    }
}