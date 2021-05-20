package ui;

import bank.Session;
import bank.SessionUtils;
import bank.commands.Deposit;
import bank.commands.Withdraw;
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

import java.io.IOException;

public class WithdrawScreen {
    private Account operatedAccount;

    @FXML
    public Button confirmWithdraw;
    @FXML
    public Button backToHomeScreen;

    @FXML
    public Label operatedAccountUUID;
    @FXML
    public TextField amountField;

    @FXML
    public void initialize() {
        Session session = Session.getInstance();
        operatedAccount = (Account) session.getFromBuffer(SessionUtils.ACCOUNT_WITHDRAW_FROM_KEY);
        System.out.println(operatedAccount.toString());
        operatedAccountUUID.setText(operatedAccount.getAccountId().toString());
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

    @FXML
    public void handleConfirmWithdraw() {
        String input = amountField.getCharacters().toString();
        if (SessionUtils.isNumeric(input)) {
            double amount = Double.parseDouble(input);
            Session session = Session.getInstance();
            Withdraw withdraw = new Withdraw(session.getCurrentClient());
            withdraw.initWithdraw(operatedAccount, amount);
            session.performCommand(withdraw);
            handleBackToHomeScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание!");
            alert.setContentText("Введите денежную сумму!");
            alert.showAndWait();
        }
    }
}
