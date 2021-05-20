package ui;

import bank.Session;
import bank.SessionUtils;
import bank.commands.Deposit;
import bank.model.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bouncycastle.util.Strings;

import java.io.IOException;

public class DepositScreen {
    private Account operatedAccount;
    @FXML
    private Button backToHomeScreen;
    @FXML
    private Button confirmDeposit;

    @FXML
    private TextField depositSum;

    @FXML
    private Label operatedAccountUUID;

    @FXML
    public void initialize() {
        Session session = Session.getInstance();
        operatedAccount = (Account) session.getFromBuffer(SessionUtils.ACCOUNT_TO_DEPOSIT_KEY);
        System.out.println(operatedAccount.toString());
        operatedAccountUUID.setText(operatedAccount.getAccountId().toString());
    }

    @FXML
    public void handleConfirmDeposit() {
        String input = depositSum.getCharacters().toString();
        if (SessionUtils.isNumeric(input)) {
            double amount = Double.parseDouble(input);

            Session session = Session.getInstance();
            Deposit deposit = new Deposit(session.getCurrentClient());
            deposit.initDeposit(operatedAccount.getAccountId(), amount);
            session.performCommand(deposit);
            handleBackToHomeScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание!");
            alert.setContentText("Введите денежную сумму!");
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
