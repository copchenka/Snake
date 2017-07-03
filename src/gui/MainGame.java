package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;

import java.util.Timer;
import java.util.TimerTask;

public class MainGame extends Application {
    private int BOARD_WIDTH = 50;
    private int BOARD_HEIGHT = 50;
    private GameDisplay gameDisplay;
    private MenuDisplay menuDisplay;
    private PauseDisplay pauseDisplay;
    private GameOverDisplay gameOverDisplay;
    private Game game;
    private Timer timer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        game = new Game(BOARD_WIDTH, BOARD_HEIGHT);
        gameDisplay = new GameDisplay(BOARD_HEIGHT, BOARD_WIDTH);
        menuDisplay = new MenuDisplay(BOARD_WIDTH * 10, BOARD_HEIGHT * 10);
        pauseDisplay = new PauseDisplay(BOARD_WIDTH * 10, BOARD_HEIGHT * 10);
        gameOverDisplay = new GameOverDisplay(BOARD_WIDTH * 10, BOARD_HEIGHT * 10);

        root.getChildren().addAll(gameDisplay, menuDisplay, pauseDisplay, gameOverDisplay);
        gameDisplay.setVisible(false);
        pauseDisplay.setVisible(false);
        gameOverDisplay.setVisible(false);
        primaryStage.setScene(scene);

        timer = new Timer();
        primaryStage.setOnCloseRequest(event -> {
            gameOver();
            System.exit(0);
        });
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    game.turn(Game.Direction.LEFT);
                    break;
                case UP:
                    game.turn(Game.Direction.UP);
                    break;
                case RIGHT:
                    game.turn(Game.Direction.RIGHT);
                    break;
                case DOWN:
                    game.turn(Game.Direction.DOWN);
                    break;
                case ESCAPE:
                    pause();
                    break;
            }
        });
        setButtons();
        primaryStage.show();
    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (game.isOver()) gameOver();
                gameDisplay.refresh(game.getBoard().getBoard(), game.getScore());
            }
        }, 0, 30);
    }

    private void pause() {
        if (game.isPause()) {
            game.resume();
            pauseDisplay.setVisible(false);
            gameDisplay.setVisible(true);
        } else {
            gameDisplay.setVisible(false);
            pauseDisplay.setVisible(true);
            game.pause();
        }
    }

    private void setButtons() {
        menuDisplay.getPlay().setOnAction(event -> {
            menuDisplay.setVisible(false);
            gameDisplay.setVisible(true);
            setUpGame();
            start();
        });
        menuDisplay.getExit().setOnAction(event -> {
            game.over();
            timer.cancel();
            System.exit(0);
        });

        pauseDisplay.getExit().setOnAction(event -> {
            pauseDisplay.setVisible(false);
            menuDisplay.setVisible(true);
        });

        pauseDisplay.getResume().setOnAction(event -> {
            pause();
        });

        gameOverDisplay.getReplay().setOnAction(event -> {
            gameOverDisplay.setVisible(false);
            gameDisplay.setVisible(true);
            setUpGame();
            start();
        });
        gameOverDisplay.getExit().setOnAction(event -> {
            gameOverDisplay.setVisible(false);
            menuDisplay.setVisible(true);
        });
    }

    private void setUpGame() {
        game = new Game(BOARD_WIDTH, BOARD_HEIGHT);
        timer = new Timer();
        game.start();
    }

    private void gameOver() {
        gameDisplay.setVisible(false);
        gameOverDisplay.setVisible(true);
        game.over();
        timer.cancel();
    }
}
