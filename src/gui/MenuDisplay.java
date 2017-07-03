package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuDisplay extends Pane{
    private Button play = new Button("Начать игру");
    private Button exit = new Button("Выйти");
    public MenuDisplay(int width, int height) {
        setPrefSize(width, height);
        VBox vBox = new VBox();
        vBox.setPrefSize(width, height);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(new Text("Змейка"));
        vBox.getChildren().add(play);
        vBox.getChildren().add(exit);
        getChildren().add(vBox);
    }

    public Button getExit() {
        return exit;
    }
    public Button getPlay() {
        return play;
    }
}
