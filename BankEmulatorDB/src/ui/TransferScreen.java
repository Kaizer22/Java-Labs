package ui;

import bank.Session;
import bank.SessionUtils;
import bank.commands.Transfer;
import bank.model.Account;
import bank.model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class TransferScreen {
    private Account operatedAccount;

    @FXML
    public Label transferFromAccountUUID;
    @FXML
    public TextField transferToAccountUUID;
    @FXML
    public TextField amountField;


    @FXML
    public Button confirmTransfer;
    @FXML
    public Button backToHomeScreen;

    @FXML
    public void initialize() {
        Session session = Session.getInstance();
        operatedAccount = (Account) session.getFromBuffer(SessionUtils.ACCOUNT_TRANSFER_FROM_KEY);
        System.out.println(operatedAccount.toString());
        transferFromAccountUUID.setText(operatedAccount.getAccountId().toString());

    }

    @FXML
    public void handleConfirmTransfer() {
        Session session = Session.getInstance();
        Client client = session.getCurrentClient();
        String toAccountUUID = transferToAccountUUID.getCharacters().toString();
        Account toAccount = session.getAccount(toAccountUUID);
        Account fromAccount = (Account) session.getFromBuffer(SessionUtils.ACCOUNT_TRANSFER_FROM_KEY);
        if ( toAccount != null) {
            String amount = amountField.getCharacters().toString();
            if (SessionUtils.isNumeric(amount)) {
                Transfer transfer = new Transfer(session.getCurrentClient());
                transfer.initTransfer(fromAccount, toAccount, Double.parseDouble(amount));
                session.performCommand(transfer);
                handleBackToHomeScreen();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание!");
            alert.setContentText("Введенный счет не существует!");
            alert.showAndWait();
        }

    }

    @FXML
    public void handleBackToHomeScreen() {
        Parent root = null;
        Stage primaryStage = Main.getPrimaryStage();
        try {
            root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
            primaryStage.setTitle("Личный кабинент Клиента");
            Scene scene = new Scene(root, 800, 400);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
