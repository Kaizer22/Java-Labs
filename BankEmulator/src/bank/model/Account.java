package bank.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Account implements Serializable {
    private UUID accountId;
    private String accountLabel;
    private Date creationTime;

    private double balance;

    protected void initAccount(){
        creationTime = new Time(System.currentTimeMillis());
        balance = 0;
        accountId = UUID.randomUUID();
        accountLabel = getAccountLabelVal();
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
}
