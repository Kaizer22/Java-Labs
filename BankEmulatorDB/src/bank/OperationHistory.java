package bank;

import bank.commands.Command;
import bank.model.OperationRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OperationHistory {
    //private static final String HISTORY_FILE_NAME = "operation_history.txt";
    private static final int HISTORY_BUFFER_SIZE = 10;
    private static final List<Command> history;

    private static class HistorySaver extends Thread {

        @Override
        public synchronized void start() {
            super.start();
            saveAndClear();
        }
    }

    static {
        history = new LinkedList<>();
    }

    public static void addToHistory(Command command) {
        if (history.size() == HISTORY_BUFFER_SIZE) {
            HistorySaver saver = new HistorySaver();
            saver.start();
        }
        history.add(command);

    }
    public static void saveAndClear(){
        Session hibernateSession = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = hibernateSession.beginTransaction();
        for (Command command: history) {
            OperationRecord operationRecord = getRecordFromCommand(command);
            System.out.println(operationRecord.toString());
            hibernateSession.save(operationRecord);
        }
        transaction.commit();
        hibernateSession.close();
        history.clear();
    }

    private static OperationRecord getRecordFromCommand(Command command) { ;
        return new OperationRecord(0L,
                command.getCommandIdentifier(),
                command.isExecuted(),
                command.getCurrentClient().toString(),
                command.getExecutionStart(),
                command.getExecutionFinish(),
                command.getCommandLog(),
                command.getDescription());
    }
}
