package rat_maze.hru0273;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Rat Maze");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 400);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        SceneController scene1Controller = loader.getController();
        scene1Controller.setPrimaryStage(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public void exitProgram(Stage primaryStage) {
        primaryStage.close();
    }
}
