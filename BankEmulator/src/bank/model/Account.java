package bank.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.UUID;

public class Account implements Serializable {
    private UUID accountId;
    private Time creationTime;

    private double balance;

    protected void initAccount(){
        creationTime = new Time(System.currentTimeMillis());
        balance = 0;
        accountId = UUID.randomUUID();
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public double getBalance() {
        return balance;
    }
}
