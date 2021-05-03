package ui;

import bank.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreen {

    @FXML
    private Button buttonSignOut;


    @FXML
    public void handleSignOut() {

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
