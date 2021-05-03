package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class CloseAccount extends Command {
    private UUID accountUUID;


    CloseAccount(Client currentClient) {
        super(currentClient);
        description = "Закрытие счета";
    }

    CloseAccount(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {
        currentClient.closeAccount(accountUUID);
    }

    public void setAccountUUID(UUID accountUUID) {
        this.accountUUID = accountUUID;
    }
}
