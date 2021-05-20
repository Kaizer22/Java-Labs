package bank;

import bank.commands.Command;
import bank.model.Account;
import bank.model.Client;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Session {
    private Client currentClient;
    private List<Client> clientsList;

    private Configuration hibernateCfg;
    private SessionFactory sessionFactory;


    private HashMap<String, Object> sessionBuffer;

    private static Session INSTANCE;

    public Session() {
        clientsList = new LinkedList<>();
        sessionBuffer = new HashMap<>();

        hibernateCfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = hibernateCfg.buildSessionFactory();

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
        org.hibernate.Session hSession = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        List clients = hSession.createQuery("from Client").list();
        for (Object client:
             clients) {
            clientsList.add((Client) client);
        }
        hSession.close();
    }

    public void saveData(){
        org.hibernate.Session hSession = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction t1 = hSession.beginTransaction();
        for (Client client:
                clientsList) {
            hSession.saveOrUpdate(client);
        }

        System.out.println("Hello");
        t1.commit();
        hSession.close();
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
