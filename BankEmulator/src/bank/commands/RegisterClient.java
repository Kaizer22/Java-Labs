package bank.commands;

import bank.model.Client;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class RegisterClient extends Command {
    private String login;
    private String password;
    private String clientName;
    private String clientSecondName;
    private String clientBirthdate;

    public RegisterClient(Client currentClient) {
        super(currentClient);
        description = "Регистрация клиента в системе банка";
    }

    public RegisterClient(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        super(commandIdentifier, currentClient, executionStart, executionFinish);
    }

    @Override
    void execute() {

    }

    public void setCredentials(String login, String password, String clientName, String clientSecondName, Date birthdate) {
        currentClient = new Client(UUID.randomUUID(), login, password, clientName,
                clientSecondName, birthdate, new LinkedList<>());
    }

    public Client getClient() {
        return currentClient;
    }
}
