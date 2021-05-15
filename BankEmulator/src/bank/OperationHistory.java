package bank;

import bank.commands.Command;

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
    private static final String HISTORY_FILE_NAME = "operation_history.txt";
    private static final int HISTORY_BUFFER_SIZE = 3;
    private static final List<Command> history;

    static {
        history = new LinkedList<>();
    }

    public static void addToHistory(Command command) {
        if (history.size() == HISTORY_BUFFER_SIZE) {
            saveAndClear();
        }
        history.add(command);

    }
    public static void saveAndClear(){
        Path file = Paths.get(HISTORY_FILE_NAME);
        List<String> buf = history.stream().map(Command::toString).collect(Collectors.toList());
        try {
            Files.write(file,buf, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        history.clear();
    }

    //TODO поиск по сохраненным командам
    public static void getCommands(Date start, Date finish) {}
    public static void getCommands(String clientFirstName, String clientSecondName) {}
    public static void getCommand(UUID commandID) {}
}
