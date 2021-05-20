package bank;

import bank.commands.Command;
import bank.commands.Login;
import bank.commands.RegisterClient;
import bank.model.Client;

import java.util.Date;

public class CredentialsManager {

    public boolean login(String login, String password) {
        Session session = Session.getInstance();
        Login commandLogin = new Login(new Client());
        commandLogin.setCredentials(login, password);
        session.performCommand(commandLogin);
        Client resultClient = commandLogin.getClient();
        if (resultClient != null) {
            session.setCurrentClient(resultClient);
            return true;
        }
        return false;
    }

    public boolean signup(String login, String password,
                       String clientName, String clientSecondName, Date birthdate) {
        Session session = Session.getInstance();
        RegisterClient commandRegister = new RegisterClient(new Client());
        commandRegister.setCredentials(login, password, clientName, clientSecondName, birthdate);
        session.performCommand(commandRegister);
        Client resultClient = commandRegister.getClient();
        if (resultClient != null) {
            session.setCurrentClient(resultClient);
            session.addClient(resultClient);
            return true;
        }
        return false;
    }
}
