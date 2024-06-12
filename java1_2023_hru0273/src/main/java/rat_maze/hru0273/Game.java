package rat_maze.hru0273;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

public class Game {
    private Rat rat;
    private MazeController mazeController;
    private Maze[] mazes;
    private int currentMazeIndex;
    private Cheese[] cheeses;
    private Canvas canvas;
    public int score = 0;
    private int cheesesTotal;


    public Game(Canvas canvas) {
        rat = new Rat(canvas,this);
        mazes = MazeInitializer.initializeMazes(canvas);
        cheeses = MazeInitializer.initializeCheeses(canvas, this);
        this.currentMazeIndex = 0;
        this.mazeController = new MazeController(canvas, mazes, cheeses);
        this.canvas = canvas;
        canvas.setOnKeyPressed(this::handleKeyPress);
        this.cheesesTotal = cheeses.length;
    }


    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        mazeController.drawCurrentMaze();
        rat.draw(gc);
        drawScore(gc);
    }

    public void simulate() {
        mazeController.moveBetweenMazes(getCanvasWidth(), getCanvasHeight(), rat);

        for (Cheese cheese : cheeses) {
            cheese = cheeses[cheese.getCurrentMazeIndex()];
            cheese.hitBy(rat);
        }

    }

    public void increaseScore(int points) {
        score += points;
        drawScore(canvas.getGraphicsContext2D());
    }

    public void drawScore(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gc.fillText("Score: " + score, 10, 30);
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode keyPressed = event.getCode();
        rat.move(keyPressed);
    }

    public double getCanvasWidth() {
        return canvas.getWidth();
    }

    public double getCanvasHeight() {
        return canvas.getHeight();
    }

    public boolean areAllCheesesEaten() {
        if (cheesesTotal == score / 10) {
            System.out.println("You Won!");
            return true;
        }
        return false;
    }

    public void saveScoreToCSV() {
        String csvFileName = "scores.csv";

        try (FileWriter writer = new FileWriter(csvFileName)) {
            writer.append("Score\n");

            writer.append(score + "\n");

            System.out.println("Score saved to " + csvFileName);
        } catch (IOException e) {
            System.out.println("Error occurred while saving score: " + e.getMessage());
        }
    }
}
