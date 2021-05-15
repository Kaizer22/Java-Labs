package bank;

import bank.commands.Command;
import bank.model.Account;
import bank.model.Client;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Handler;

public class Session {
    private Client currentClient;
    private List<Client> clientsList;

    private HashMap<String, Object> sessionBuffer;

    private static Session INSTANCE;

    public Session() {
        clientsList = new LinkedList<>();
        sessionBuffer = new HashMap<>();
        restoreData();
    }

    public static synchronized Session getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Session();
        }
        return INSTANCE;
    }

    public void initSession(Client client) {
        this.currentClient = client;
    }

    protected void addClient(Client client) {
        clientsList.add(client);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void performCommand(Command command) {
        for (Client client:
             clientsList) {
            System.out.println(client.toString());
        }
        command.safeExecute();
        OperationHistory.addToHistory(command);
    }

    public void restoreData(){
        FileInputStream fis;
        ObjectInputStream oin;
        try {
            fis = new FileInputStream("clients.out");
            oin = new ObjectInputStream(fis);
            while (true) {
                Client client = (Client) oin.readObject();
                clientsList.add(client);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveData(){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("clients.out");
            oos = new ObjectOutputStream(fos);
            for (Client client: clientsList) {
                oos.writeObject(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        OperationHistory.saveAndClear();
    }

    public Client getClient(String login, String password) {
        for (Client cl: clientsList) {
            if (cl.getLogin().equals(login) && cl.getPassword().equals(password)) {
                return cl;
            }
        }
        return null;
    }

    public Account getAccount(String accountUUID) {
        for (Client client: clientsList) {
            for (Account account:client.getAccountList()) {
                if (account.getAccountId().toString().equals(accountUUID)) {
                    return account;
                }
            }
        }
        return null;
    }

    public void setCurrentClient(Client client) {
        currentClient = client;
    }

    public Object getFromBuffer(String key) {
        return  sessionBuffer.get(key);
    }
    public void putToBuffer(String key, Object value) {
        sessionBuffer.put(key, value);
    }
}
