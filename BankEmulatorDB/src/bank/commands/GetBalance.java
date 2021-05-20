package bank.commands;

import bank.model.Account;
import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class GetBalance extends Command {
    private UUID accountUUID;
    private double balance;

    GetBalance(Client currentClient) {
        super(currentClient);
        description = "Запрос остатка по счету";
    }

    GetBalance(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {
        Account account = currentClient.getAccountByUUID(accountUUID);
        if (account == null) {
            commandLog = commandLog.concat(getLogPrefix().concat("Cannot find account %s".formatted(accountUUID.toString())));
        } else {
            balance = account.getBalance();
        }
    }

    public void setAccountUUID(UUID accountUUID) {
        this.accountUUID = accountUUID;
    }

    public double getBalance() {
        return balance;
    }




}
