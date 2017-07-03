package gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Board;

import java.util.ArrayList;
import java.util.List;

public class GameDisplay extends Pane {
    private int rows;
    private int columns;
    private List<List<Rectangle>> rectangles;
    private Text text;

    public GameDisplay(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        setPrefSize(rows * 10, columns * 10);
        rectangles = new ArrayList<>();
        text = new Text("Очков: 0");
        for (int i = 0; i < rows; i++) {
            rectangles.add(i, new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                rectangles.get(i).add(j, new Rectangle(10, 10, Color.WHITE));
                getChildren().add(rectangles.get(i).get(j));
                rectangles.get(i).get(j).setTranslateX(i * 10);
                rectangles.get(i).get(j).setTranslateY(j * 10);

            }
        }
        getChildren().add(text);
        text.setTranslateX(2 * 10);
        text.setTranslateY((rows - 2) * 10);
    }

    public void refresh(Board.Cell[][] cells, int score) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Board.Cell cell = cells[j][i];
                if (cell == Board.Cell.SNAKE) rectangles.get(i).get(j).setFill(Color.BLUE);
                else if (cell == Board.Cell.FOOD) rectangles.get(i).get(j).setFill(Color.RED);
                else if (cell == Board.Cell.BARRIER) rectangles.get(i).get(j).setFill(Color.BLACK);
                else rectangles.get(i).get(j).setFill(Color.WHITE);
            }
        }
        text.setText("Очков: " + score);
    }
}
