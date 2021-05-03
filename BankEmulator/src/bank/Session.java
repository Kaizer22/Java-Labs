package bank;

import bank.commands.Command;
import bank.model.Client;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Session {
    private Client currentClient;
    private List<Client> clientsList;

    private static Session INSTANCE;

    public Session() {
        clientsList = new LinkedList<>();
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



    }

    public Client getClient(String login, String password) {
        for (Client cl: clientsList) {
            if (cl.getLogin().equals(login) && cl.getPassword().equals(password)) {
                return cl;
            }
        }
        return null;
    }

    public void setCurrentClient(Client client) {
        currentClient = client;
    }
}
