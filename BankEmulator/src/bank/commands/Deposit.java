package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class Deposit extends Command {
    Deposit(Client currentClient) {
        super(currentClient);
        description = "Внесение средств на счет";
    }

    Deposit(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {

    }
}
