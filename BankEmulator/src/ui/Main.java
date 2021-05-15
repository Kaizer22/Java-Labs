package ui;

import bank.Session;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Main extends Application {
    Session currentSession;
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("start_screen.fxml"));
        Main.primaryStage = primaryStage;
        currentSession = Session.getInstance();
        primaryStage.setTitle("Вход");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> {
            if (windowEvent.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST) {
                    currentSession.saveData();
            }
        });
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }




    public static void main(String[] args) {
        launch(args);
    }
}
