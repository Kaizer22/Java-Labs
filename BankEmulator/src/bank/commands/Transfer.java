package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class Transfer extends Command {
    Transfer(Client currentClient) {
        super(currentClient);
        description = "Перевод между счетами";
    }

    Transfer(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {

    }
}
