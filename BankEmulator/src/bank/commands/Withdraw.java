package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class Withdraw extends Command {
    Withdraw(Client currentClient) {
        super(currentClient);
        description = "Снятие денег со счета";
    }

    Withdraw(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {

    }
}
