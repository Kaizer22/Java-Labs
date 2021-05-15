package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class Deposit extends Command {
    private double amount;
    private UUID accountUUID;

    public Deposit(Client currentClient) {
        super(currentClient);
        description = "Внесение средств на счет";
    }

    Deposit(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {
        currentClient.getAccountByUUID(accountUUID).makeDeposit(amount);
        commandLog = commandLog.concat(getLogPrefix()
                .concat("На счет %s зачислено %.2f у.е.".formatted(accountUUID.toString(), amount))
        );
    }

    public void initDeposit(UUID accountUUID, double amount) {
        this.amount = amount;
        this.accountUUID = accountUUID;
    }


}
