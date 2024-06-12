package rat_maze.hru0273;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class Scene2 {
    @FXML
    private Label timeLabel;
    @FXML
    private Canvas mazeCanvas;
    private Timeline timeline;
    private final int STARTTIME = 75;
    private javafx.beans.property.IntegerProperty timeSeconds = new javafx.beans.property.SimpleIntegerProperty(STARTTIME);
    private DrawingThread drawingThread;
    private Game game;

    public void initialize() {
        game = new Game(mazeCanvas);
        drawingThread = new DrawingThread(mazeCanvas, game);
        drawingThread.start();

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();

        timeLabel.textProperty().bind(timeSeconds.asString());

        timeSeconds.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() <= 0) {
                stopGame();
            }else if(drawingThread.getGame().areAllCheesesEaten()){
                stopGame();
            }
        });
    }

        void stopGame() {
            timeline.stop();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameEndScreen.fxml"));
                AnchorPane root = loader.load();

                GameEndScreenController controller = loader.getController();
                controller.setStage((Stage) timeLabel.getScene().getWindow());

                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) timeLabel.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            game.saveScoreToCSV();
        }


}


