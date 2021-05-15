package bank.commands;

import bank.model.Account;
import bank.model.Client;

import java.sql.Time;
import java.util.UUID;

public class Withdraw extends Command {
    private Account withdrawFrom;
    private double amount;

    public Withdraw(Client currentClient) {
        super(currentClient);
        description = "Снятие денег со счета";
    }

    Withdraw(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {
        if (!withdrawFrom.withdraw(amount)) {
            commandLog = commandLog.concat(getLogPrefix().concat(
                    "Невозможно выполнить операцию: на счете %s недостаточно средств"
                            .formatted(withdrawFrom.getAccountId().toString())
            ));
        }
    }

    public void initWithdraw(Account withdrawFrom, double amount) {
        this.amount = amount;
        this.withdrawFrom = withdrawFrom;
    }
}
