package sample.educationAnimation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainApp extends Application {
    ArrayList<Label> aLabels = new ArrayList<Label>();
    final private int digitCount = 7;//+1
    final private int maxCount = 123456;//maximal decimal count

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Animation Timer Example");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(25);

        for (int i = 0; i <= digitCount; i++) {
            aLabels.add(new Label("0"));
            aLabels.get(i).setFont(new Font("Arial", 30));
            aLabels.get(i).setStyle("" +
                    "-fx-padding: 5;" +
                    "-fx-border-color: green;" +
                    "-fx-border-radius: 5;");
            hBox.getChildren().add(aLabels.get(i));
        }

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button reset = new Button("Reset");

        //start.setStyle("" +
        //"-fx-border-radius:10;" +
        //"-fx-background-color-yellow;");

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //longProcess();
                timer.start();
            }
        });
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop();
            }
        });
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i <= digitCount; i++) {
                    aLabels.get(i).setText("0");
                    hBox.getChildren().set(i, aLabels.get(i));
                    counter = 0;
                }
            }
        });

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);
        buttons.getChildren().addAll(start, stop, reset);

        vBox.getChildren().addAll(hBox, buttons);

        primaryStage.setScene(new Scene(vBox, 55 * (digitCount + 1), 100));
        primaryStage.show();
    }

    private void longProcess() {
        int digit;
        for (int i = 0; i <= this.maxCount; i++) {
            for (int j = 0; j <= digitCount; j++) {
                digit = (int) (i % (Math.pow(10.0, (double) (j + 1))));
                digit = (int) (digit / (Math.pow(10.0, (double) j)));
                this.aLabels.get(digitCount - j).setText(Integer.toString(digit));
            }
        }
    }

    private int counter;

    private void longProcessAnimated() {
        if (counter > maxCount) {
            timer.stop();
            return;
        }
        int digit;
        for (int j = 0; j <= digitCount; j++) {
            digit = (int) (counter % (Math.pow(10.0, (double) (j + 1))));
            digit = (int) (digit / (Math.pow(10.0, (double) j)));
            this.aLabels.get(digitCount - j).setText(Integer.toString(digit));
        }
        counter++;
    }

    private void clock() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String now = dateFormat.format(dateNow);
        for (int j = 0; j < now.length(); j++) {
            //for (int skip = 0; skip < 100000; skip++)
            this.aLabels.get(j).setText(now.substring(j, j + 1));
        }
        //System.out.println(now);
        //System.out.println(now.length());
    }

    protected AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            //longProcessAnimated();
            clock();
        }
    };

}
