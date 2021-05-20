package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class GetReport extends Command {
    GetReport(Client currentClient) {
        super(currentClient);
        description = "Запрос отчета по счетам клиента";
    }

    GetReport(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {

    }
}
