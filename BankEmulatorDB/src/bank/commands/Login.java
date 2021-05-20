package bank.commands;

import bank.Session;
import bank.model.Account;
import bank.model.Client;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class Login extends Command{
    private String login;
    private String password;

    public Login(Client currentClient) {
        super(currentClient);
        description = "Попытка входа в систему";
    }

    public Login(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    public void setCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Client getClient() {
        return currentClient;
    }

    @Override
    void execute() {
        Session session = Session.getInstance();
        currentClient = session.getClient(login, password);
        if (currentClient == null) {
            commandLog = commandLog.concat(getLogPrefix().concat("Client not found"));
        }
    }

}
