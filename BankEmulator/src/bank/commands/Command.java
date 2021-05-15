package bank.commands;

import bank.model.Client;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public abstract class Command implements Serializable {

    private UUID commandIdentifier;
    private boolean isExecuted;

    protected Client currentClient;
    private UUID performerUUID;

    private Time executionStart;
    private Time executionFinish;

    protected String commandLog;
    protected String description;

    Command(Client currentClient) {
        this.currentClient = currentClient;
        commandIdentifier = UUID.randomUUID();
        commandLog = "";
    }

    Command(UUID commandIdentifier, Client currentClient, Time executionStart, Time executionFinish) {
        isExecuted = true;
        this.commandIdentifier = commandIdentifier;
        this.currentClient = currentClient;
        this.executionStart = executionStart;
        this.executionFinish = executionFinish;
    }

     public void safeExecute(){
        if (!isExecuted) {
            execute();
            isExecuted = true;
            if (currentClient != null) {
                performerUUID = currentClient.getUuid();
            }
        } else {
            commandLog = commandLog.concat(getLogPrefix() +
                    "You cannot run a single instance of command twice\n");
        }
    }

    protected String getLogPrefix(){
        return String.format("[%s:%s:%s]",new Date().toString(),
                commandIdentifier.toString(), description);
    }

    abstract void execute();

    @Override
    public String toString() {
        return "Command{" +
                "commandIdentifier=" + commandIdentifier +
                ", isExecuted=" + isExecuted +
                ", currentClient=" + currentClient +
                ", executionStart=" + executionStart +
                ", executionFinish=" + executionFinish +
                ", commandLog='" + commandLog + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
