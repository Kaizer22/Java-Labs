package ui;

import bank.Session;
import bank.SessionUtils;
import bank.commands.CloseAccount;
import bank.commands.Command;
import bank.commands.OpenAccount;
import bank.model.Account;
import bank.model.AccountsReport;
import bank.model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomeScreen {

    @FXML
    private Button buttonSignOut;
    @FXML
    private Button createAccount;
    @FXML
    private Button formReport;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label secondNameLabel;

    @FXML
    private GridPane accountListGridPane;



    @FXML
    public void initialize() {
        Session session = Session.getInstance();
        firstNameLabel.setText(session.getCurrentClient().getFirstName());
        secondNameLabel.setText(session.getCurrentClient().getSecondName());
        renderAccountList(session.getCurrentClient().getAccountList());
    }

    private void renderAccountList(List<Account> accountList) {
        int i = 0;
        accountListGridPane.getChildren().clear();
        for (Account account: accountList) {
            addAccountRow(account, i);
            i++;
        }
    }

    private void addAccountRow(Account account, int i) {
        Button depositButton = new Button();
        depositButton.setOnAction(actionEvent -> processDeposit(account));
        depositButton.setText("Пополнить");
        
        Button closeButton = new Button();
        closeButton.setOnAction(actionEvent -> closeAccount(account));
        closeButton.setText("X");
        
        Button transferButton = new Button();
        transferButton.setOnAction(actionEvent -> processTransfer(account));
        transferButton.setText("Перевод");

        Label accountBalance = new Label();
        accountBalance.setText("%.2f".formatted(account.getBalance()));
        
        Label accountUUID = new Label();
        accountUUID.setText(account.getAccountId().toString());
        
        
        Button withdrawButton = new Button();
        withdrawButton.setOnAction(actionEvent -> processWithdraw(account));
        withdrawButton.setText("Вывести");
        
        
        accountListGridPane.add(accountUUID, 0, i);
        accountListGridPane.add(accountBalance, 1, i);
        accountListGridPane.add(depositButton, 2, i);
        accountListGridPane.add(closeButton, 3, i);
        accountListGridPane.add(transferButton, 4, i);
        accountListGridPane.add(withdrawButton, 5, i);

    }

    private void processWithdraw(Account account) {
        Parent root = null;
        Session session = Session.getInstance();
        session.putToBuffer(SessionUtils.ACCOUNT_WITHDRAW_FROM_KEY, account);
        Stage primaryStage = Main.getPrimaryStage();
        try {
            root = FXMLLoader.load(getClass().getResource("withdraw_screen.fxml"));
            primaryStage.setTitle("Вывод средств");
            primaryStage.setScene(new Scene(root, 400, 180));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processTransfer(Account account) {
        Parent root = null;
        Session session = Session.getInstance();
        session.putToBuffer(SessionUtils.ACCOUNT_TRANSFER_FROM_KEY, account);
        Stage primaryStage = Main.getPrimaryStage();
        try {
            root = FXMLLoader.load(getClass().getResource("transfer_screen.fxml"));
            primaryStage.setTitle("Перевод средств");
            primaryStage.setScene(new Scene(root, 400, 200));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processDeposit(Account account) {
        Parent root = null;
        Session session = Session.getInstance();
        session.putToBuffer(SessionUtils.ACCOUNT_TO_DEPOSIT_KEY, account);
        Stage primaryStage = Main.getPrimaryStage();
        try {
            root = FXMLLoader.load(getClass().getResource("deposit_screen.fxml"));
            primaryStage.setTitle("Пополнить счет");
            primaryStage.setScene(new Scene(root, 400, 150));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closeAccount(Account account) {
        Session session = Session.getInstance();
        Client currentClient = session.getCurrentClient();
        CloseAccount closeAccount = new CloseAccount(currentClient);
        closeAccount.setAccountUUID(account.getAccountId());
        session.performCommand(closeAccount);

        renderAccountList(currentClient.getAccountList());
    }


    @FXML
    public void handleSignOut() {
        startStartScreen();
    }

    @FXML
    public void handleOpenAccount() {
        Session session = Session.getInstance();
        Client client = session.getCurrentClient();

        Command openAccount = new OpenAccount(client);
        session.performCommand(openAccount);

        renderAccountList(client.getAccountList());
    }

    @FXML
    public void handleFormReport() {
        AccountsReport accountsReport = new AccountsReport();
        Session session = Session.getInstance();
        accountsReport.formReport(session.getCurrentClient());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Отчет сформирован");
        alert.setContentText(String.format("Файл %s успешно сохранен",
                accountsReport.getReportPath()));
        alert.showAndWait();
    }

    private void startStartScreen() {
        Parent root = null;
        Stage primaryStage = Main.getPrimaryStage();
        try {
            root = FXMLLoader.load(getClass().getResource("start_screen.fxml"));
            Session session = Session.getInstance();
            session.setCurrentClient(null);
            primaryStage.setTitle("Вход");
            primaryStage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
