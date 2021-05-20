package bank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID accountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_list_id")
    private Client owner;

    private String accountLabel;

    private Date creationTime;
    private double balance;

    public Account() {}


    public Account(UUID accountId, Client owner, String accountLabel, Date creationTime, double balance) {
        this.accountId = accountId;
        this.owner = owner;
        this.accountLabel = accountLabel;
        this.creationTime = creationTime;
        this.balance = balance;
    }


    protected void initAccount(Client client){
        creationTime = new Time(System.currentTimeMillis());
        balance = 0;
        accountId = UUID.randomUUID();
        accountLabel = getAccountLabelVal();
        owner = client;
    }

    public void makeDeposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance - amount >= 0) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    private String getAccountLabelVal() {
        String uuid = accountId.toString();
        int sum = 0;
        for (int i = 0; i < uuid.length(); i++) {
            sum+=uuid.charAt(i);
        }
        return "Account " + sum/100;
    }

    public String getAccountLabel() {
        return accountLabel;
    }


    public UUID getAccountId() {
        return accountId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public double getBalance() {
        return balance;
    }

    public Long getDb_id() {
        return id;
    }

    public void setDb_id(Long db_id) {
        this.id = db_id;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public void setAccountLabel(String accountLabel) {
        this.accountLabel = accountLabel;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
