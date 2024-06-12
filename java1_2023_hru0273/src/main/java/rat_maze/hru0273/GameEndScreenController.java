package rat_maze.hru0273;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public GameEndScreenController(){}
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handlePlayAgainButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        stage.close();
    }

}

