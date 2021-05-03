package ui;

import bank.CredentialsManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class StartScreen {

    @FXML
    private TextField loginSignIn;
    @FXML
    private TextField passwordSignIn;
    @FXML
    private Button buttonSignIn;

    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField loginSignUp;
    @FXML
    private TextField passwordSignUp;
    @FXML
    private DatePicker birthDate;
    @FXML
    private Button buttonSignUp;

    @FXML
    public void handleSignIn() {
        String login = loginSignIn.getCharacters().toString();
        String password = passwordSignIn.getCharacters().toString();
        CredentialsManager credentialsManager = new CredentialsManager();

        if (credentialsManager.login(login, password)) {
            System.out.println("Signed In");
            startHomeScreen();
        }

    }

    @FXML
    public void handleSignUp() {
        String firstNameStr = firstName.getCharacters().toString();
        String secondNameStr = secondName.getCharacters().toString();
        String login = loginSignUp.getCharacters().toString();
        String password = passwordSignUp.getCharacters().toString();
        LocalDate birthDateVal = birthDate.getValue();
        Date date = Date.from(birthDateVal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        CredentialsManager credentialsManager = new CredentialsManager();

        if (credentialsManager.signup(login, password, firstNameStr, secondNameStr, date)) {
            System.out.println("Signed Up");
            startHomeScreen();
        }
    }

    private void startHomeScreen() {
        Parent root = null;
        Stage primaryStage = Main.getPrimaryStage();
        try {
            root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
            primaryStage.setTitle("Личный кабинент Клиента");
            primaryStage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
