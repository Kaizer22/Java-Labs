package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class OpenAccount extends Command {
    public OpenAccount(Client currentClient) {
        super(currentClient);
        description = "Открытие клиентского счета";
    }

    OpenAccount(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {
        currentClient.createAccount();
    }
}
