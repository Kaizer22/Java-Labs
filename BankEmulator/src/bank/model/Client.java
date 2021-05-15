package bank.model;

import bank.commands.Command;
import bank.commands.Transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Client implements Serializable {
    private final String DEFAULT_NO_CLIENT_UUID = "11111111-1111-1111-1111-111111111111";

    private UUID uuid;
    public UUID getUuid() {
        return uuid;
    }
    private String firstName;
    private String secondName;
    private Date birthdate;
    private String login;
    private String password;


    private List<Account> accountList;

    public Client() {
        uuid = UUID.fromString(DEFAULT_NO_CLIENT_UUID);
    }

    public Client(UUID uuid, String login, String password, String firstName, String secondName, Date birthdate, List<Account> accountList) {
        this.uuid = uuid;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdate = birthdate;
        this.accountList = accountList;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void createAccount() {
        Account bufAccount = new Account();
        bufAccount.initAccount();
        accountList.add(bufAccount);
    }

    public void closeAccount(UUID accountUUID) {
        for (Account acc:
                accountList) {
            if (accountUUID.toString().equals(acc.getAccountId().toString())) {
                accountList.remove(acc);
                return;
            }
        }
    }

    public Account getAccountByUUID(UUID accountUUID) {
        for (Account acc:
             accountList) {
            if (accountUUID.toString().equals(acc.getAccountId().toString())) {
                return acc;
            }
        }
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<Account> getAccountList() {
        return new ArrayList<>(accountList);
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthdate=" + birthdate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
