package bank.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "operation_history")
public class OperationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID identifier;

    private boolean isExecuted;

    @Column(length = 2048)
    private String currentClientInfo;

    private Date executionStart;

    private Date getExecutionFinish;

    @Column(length = 2048)
    private String commandLog;

    private String description;

    public OperationRecord() {}

    public OperationRecord(Long id, UUID identifier, boolean isExecuted, String currentClientInfo, Date executionStart, Date getExecutionFinish, String commandLog, String description) {
        this.id = id;
        this.identifier = identifier;
        this.isExecuted = isExecuted;
        this.currentClientInfo = currentClientInfo;
        this.executionStart = executionStart;
        this.getExecutionFinish = getExecutionFinish;
        this.commandLog = commandLog;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

    public String getCurrentClientInfo() {
        return currentClientInfo;
    }

    public void setCurrentClientInfo(String currentClientInfo) {
        this.currentClientInfo = currentClientInfo;
    }

    public Date getExecutionStart() {
        return executionStart;
    }

    public void setExecutionStart(Date executionStart) {
        this.executionStart = executionStart;
    }

    public Date getGetExecutionFinish() {
        return getExecutionFinish;
    }

    public void setGetExecutionFinish(Date getExecutionFinish) {
        this.getExecutionFinish = getExecutionFinish;
    }

    public String getCommandLog() {
        return commandLog;
    }

    public void setCommandLog(String commandLog) {
        this.commandLog = commandLog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OperationRecord{" +
                "id=" + id +
                ", identifier=" + identifier +
                ", isExecuted=" + isExecuted +
                ", currentClientInfo='" + currentClientInfo + '\'' +
                ", executionStart=" + executionStart +
                ", getExecutionFinish=" + getExecutionFinish +
                ", commandLog='" + commandLog + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
