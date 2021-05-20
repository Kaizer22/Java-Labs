package bank.commands;

import bank.model.Account;
import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class Transfer extends Command {
    private Account fromAccount;
    private Account toAccount;
    private double amount;

    public Transfer(Client currentClient) {
        super(currentClient);
        description = "Перевод между счетами";
    }

    Transfer(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {
        if (fromAccount.withdraw(amount)) {
            toAccount.makeDeposit(amount);
        } else {
            commandLog = commandLog.concat(getLogPrefix()
                    .concat("Невозможно выполнить операцию: на счете %s недостаточно средств"
                    .formatted(fromAccount.getAccountId().toString())
                    )
            );
        }
    }

    public void initTransfer(Account fromAccount, Account toAccount, double amount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }
}
