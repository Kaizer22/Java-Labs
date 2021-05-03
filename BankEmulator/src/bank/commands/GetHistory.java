package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class GetHistory extends Command {
    GetHistory(Client currentClient) {
        super(currentClient);
        description = "Запрос на историю операций";
    }

    GetHistory(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {

    }
}
