package animation;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Controller {
    private Timeline animationTimeline;
    private AnimationTimer animationTimer;
    private Random r;

    @FXML
    private Canvas canvasField;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonStop;

    @FXML
    private Button buttonExit;

    @FXML
    public void initialize() {
        r = new Random();
        IntegerProperty type  = new SimpleIntegerProperty();
        double W = canvasField.getWidth();
        double H = canvasField.getHeight();
        type.addListener((observableValue, number, t1) -> {
            //Draws new figure only when type value changes
            GraphicsContext gc = canvasField.getGraphicsContext2D();
            switch (type.get()) {
                case 0 -> drawSquare(gc, W, H);
                case 1 -> drawCircle(gc, W, H);
                case 2 -> drawTriangle(gc, W, H);
            }
        });

        //Interpolates type value change (from 0 to 2) on duration (6 seconds)
        animationTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(type, 0)
                ),
                new KeyFrame(Duration.seconds(6),
                        new KeyValue(type, 2)
                )
        );
        animationTimeline.setAutoReverse(true);
        animationTimeline.setCycleCount(Timeline.INDEFINITE);

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Here we can handle every frame redraw, but there is no need
            }
        };
    }

    @FXML
    public void handleStart() {
        animationTimer.start();
        animationTimeline.play();
    }

    @FXML
    public void handleStop(){
        animationTimer.stop();
        animationTimeline.stop();
    }

    @FXML
    public void handleExit() {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    private void drawCircle(GraphicsContext gc, double W, double H) {
        gc.clearRect(0, 0, W, H);
        gc.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255) ));
        gc.fillOval(W/2-H/2,0,W/2+H/2, H);
    }

    private void drawSquare(GraphicsContext gc, double W, double H) {
        gc.clearRect(0, 0, W, H);
        gc.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255) ));
        gc.setLineWidth(1.0);
        gc.beginPath();
        gc.moveTo(0, 0);
        gc.lineTo(W, 0);
        gc.lineTo(W, H);
        gc.lineTo(0, H);
        gc.closePath();
        gc.stroke();
        gc.fill();

    }
    private void drawTriangle(GraphicsContext gc, double W, double H) {
        gc.clearRect(0, 0, W, H);
        gc.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255) ));
        gc.setLineWidth(1.0);
        gc.beginPath();
        gc.moveTo(0, 0);
        gc.lineTo(W, 0);
        gc.lineTo(W, H);
        gc.closePath();
        gc.stroke();
        gc.fill();
    }
}
