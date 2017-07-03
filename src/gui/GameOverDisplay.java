package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class GameOverDisplay extends Pane {
    private Button replay = new Button("Начать заново");
    private Button exit = new Button("Выйти");

    public GameOverDisplay(int width, int height) {
        setPrefSize(width, height);
        VBox vBox = new VBox();
        vBox.setPrefSize(width, height);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(new Text("Игра завершена"));
        vBox.getChildren().add(replay);
        vBox.getChildren().add(exit);
        getChildren().add(vBox);
    }

    public Button getExit() {
        return exit;
    }

    public Button getReplay() {
        return replay;
    }
}
