package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PauseDisplay extends Pane {
    private Button resume = new Button("Продолжить");
    private Button exit = new Button("Выйти");
    public PauseDisplay(int width, int height) {
        setPrefSize(width, height);
        VBox vBox = new VBox();
        vBox.setPrefSize(width, height);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(new Text("Пауза"));
        vBox.getChildren().add(resume);
        vBox.getChildren().add(exit);
        getChildren().add(vBox);
    }

    public Button getExit() {
        return exit;
    }
    public Button getResume() {
        return resume;
    }
}
