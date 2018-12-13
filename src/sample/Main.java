package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.view.Controller;

public class Main extends Application {
    private Scene scene;
    private Controller controller;
    boolean up, down, left, right;

    public Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/sample.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.setMain(this);
        scene = new Scene(root, Controller.cellSize * Controller.gridSize, Controller.cellSize * Controller.gridSize);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        up = true;
                        controller.upCase();
                        break;
                    case DOWN:
                        down = true;
                        controller.downCase();
                        break;
                    case LEFT:
                        left = true;
                        controller.leftCase();
                        break;
                    case RIGHT:
                        right = true;
                        controller.rightCase();
                        break;
                    case ENTER:
                        controller.firstAnimation();
                        break;
                    case ESCAPE:
                        controller.start();
                        break;
                }
            }
        });
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
