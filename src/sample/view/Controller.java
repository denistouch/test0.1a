package sample.view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    @FXML
    private AnchorPane anchorPane;
    private GridPane grid;
    private Main main;
    private int positionX;
    private int positionY;
    private int indexGridCell;
    public static final int gridSize = 32;
    public static final double cellSize = 20;

    public void setMain(Main main) {
        this.main = main;
    }

    public Controller() {
    }

    @FXML
    private void initialize() {
        grid = new GridPane();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Pane cellPane = new Pane();
                cellPane.setPrefSize(cellSize, cellSize);
                grid.add(cellPane, i, j);
            }
        }
        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() < 2)
                    return;

            }
        });
        anchorPane.getChildren().add(grid);
        System.out.println(grid.getChildren().size());
    }

    public void flush() {
        for (Node node : grid.getChildren()) {
            node.setStyle(anchorPane.getStyle());
        }
        timer.stop();
    }

    public void firstAnimation() {
        timer.start();
    }

    protected AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            //ukraineFlag();
            clock();
        }
    };

    private void clock() {
        if (indexGridCell == grid.getChildren().size() - 1)
            timer.stop();
        //return;
        Node node = grid.getChildren().get(indexGridCell);
        node.setStyle("-fx-background-color: yellow");
        node = grid.getChildren().get(grid.getChildren().size() - 1 - indexGridCell);
        node.setStyle("-fx-background-color: blue");

        positionY = indexGridCell - (positionX * gridSize);
        if (indexGridCell % gridSize == 0 && indexGridCell != 0)
            positionX++;
        System.out.println("indexGridCell: (" + indexGridCell + ") Coordinates (x: " + positionX + ",y: " + positionY + ")");
        indexGridCell++;
    }

    private void ukraineFlag() {
        if (indexGridCell == grid.getChildren().size() - 1)
            timer.stop();
        indexGridCell = positionY * gridSize + positionX;
        Node node = grid.getChildren().get(indexGridCell);
        node.setStyle("-fx-background-color: yellow");
        node = grid.getChildren().get(grid.getChildren().size()-1-indexGridCell);
        node.setStyle("-fx-background-color: blue");
        if (positionY < gridSize - 1) {
            positionY++;
        }
        else if (positionX < gridSize - 1) {
            positionY = 0;
            positionX++;
        }

    }

    public void start() {
        positionY = 0;
        positionX = 0;
        indexGridCell = 0;
        flush();
        //update();
    }

    public void leftCase() {
        if (positionX == 0)
            return;
        positionX--;
        update();
    }

    public void rightCase() {
        if (positionX == gridSize - 1)
            return;
        positionX++;
        update();
    }

    public void upCase() {
        if (positionY == 0)
            return;
        positionY--;
        update();
    }

    public void downCase() {
        if (positionY == gridSize - 1)
            return;
        positionY++;
        update();
    }

    public void update() {

        Node node = grid.getChildren().get(positionY + positionX * gridSize);
        node.setStyle("-fx-background-color: red");
    }
}
