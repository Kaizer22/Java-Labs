package bank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "client")
public class Client implements Serializable {
    //Столкнулся с проблемой, когда брал как id тип UUID, поэтому введено
    //дополнительное поле - id записи в БД
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private final String DEFAULT_NO_CLIENT_UUID = "11111111-1111-1111-1111-111111111111";

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountList(Set<Account> accountList) {
        this.accountList = accountList;
    }


    private UUID uuid;
    public UUID getUuid() {
        return uuid;
    }

    private String firstName;

    private String secondName;

    private Date birthdate;

    private String login;

    private String password;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Account> accountList;

    public Client() {
        uuid = UUID.fromString(DEFAULT_NO_CLIENT_UUID);
    }

    public Client(UUID uuid, String login, String password, String firstName, String secondName, Date birthdate, Set<Account> accountList) {
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
        bufAccount.initAccount(this);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                '}';
    }
}
