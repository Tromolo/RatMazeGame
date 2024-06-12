package rat_maze.hru0273;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class MazeController {
    private Maze[] mazes;
    private int currentMazeIndex;
    private Maze currentMaze;
    private Cheese[] cheeses;
    private Cheese currentCheese;
    private Canvas canvas;
    private boolean[] inMaze = new boolean[9];

    public MazeController(Canvas canvas, Maze[] mazes, Cheese[] cheeses) {
        this.canvas = canvas;
        this.mazes = mazes;
        this.cheeses = cheeses;
        this.currentMazeIndex = 0;
        this.currentMaze = mazes[currentMazeIndex];
        this.currentCheese = cheeses[currentMazeIndex];
        inMaze[0] = true;
        for (int i = 1; i < 9; i++){
            inMaze[i] = false;
        }
    }

    public void switchMaze(int mazeIndex) {
        if (mazeIndex >= 0 && mazeIndex < mazes.length) {
            currentMazeIndex = mazeIndex;
            currentMaze = mazes[currentMazeIndex];
            currentCheese = cheeses[currentMazeIndex];
        }
    }

    public void drawCurrentMaze() {
        currentCheese.draw(canvas.getGraphicsContext2D());
        currentMaze.draw(canvas.getGraphicsContext2D());
    }

    public void moveBetweenMazes(double canvasWidth, double canvasHeight, Rat rat) {
        double ratWidth = rat.getRatWidth();
        double ratHeight = rat.getRatHeight();

        Point2D point =rat.getPosition();
        double xPos = point.getX();
        double yPos = point.getY();


        if (inMaze[0] && xPos < 0){
            moveToMaze(0, 1, canvasWidth - ratWidth, yPos,rat);
        } else if (inMaze[0] && xPos == canvasWidth) {
            moveToMaze(0, 4, 0, yPos,rat);
        } else if (inMaze[1] && xPos == canvasWidth) {
            moveToMaze(1, 0, 0, yPos,rat);
        } else if (inMaze[1] && yPos <  0) {
            moveToMaze(1, 2, xPos, canvasHeight - ratHeight,rat);
        } else if (inMaze[2] && yPos == canvasHeight) {
            moveToMaze(2, 1, xPos, 0,rat);
        } else if (inMaze[2] && xPos == canvasWidth) {
            moveToMaze(2, 3, 0, yPos,rat);
        } else if (inMaze[3] && xPos <  0) {
            moveToMaze(3, 2, canvasWidth - ratWidth, yPos,rat);
        } else if (inMaze[4] && xPos < 0){
            moveToMaze(4, 0, canvasWidth - ratWidth, yPos,rat);
        } else if (inMaze[4] && yPos <  0) {
            moveToMaze(4, 5, xPos, canvasHeight - ratHeight,rat);
        } else if (inMaze[4] && yPos == canvasHeight) {
            moveToMaze(4, 6, xPos, 0,rat);
        } else if (inMaze[5] && yPos == canvasHeight) {
            moveToMaze(5, 4, xPos, 0,rat);
        } else if (inMaze[6] && yPos <  0) {
            moveToMaze(6, 4, xPos, canvasHeight - ratHeight,rat);
        } else if (inMaze[6] && xPos < 0) {
            moveToMaze(6, 7, canvasWidth - ratWidth, yPos,rat);
        } else if (inMaze[7] && xPos == canvasWidth) {
            moveToMaze(7, 6, 0, yPos,rat);
        } else if (inMaze[7] && xPos < 0) {
            moveToMaze(7, 8, canvasWidth - ratWidth, yPos,rat);
        } else if (inMaze[8] && xPos == canvasWidth) {
            moveToMaze(8, 7, 0, yPos,rat);
        }
    }

    private void moveToMaze(int currentMaze, int nextMaze, double x, double y,Rat rat) {
        inMaze[currentMaze] = false;
        inMaze[nextMaze] = true;

        rat.setPosition(new Point2D(x, y));
        switchMaze(nextMaze);
        rat.setCurrentMazeIndex(nextMaze);

        for (Cheese cheese : cheeses) {
            cheese.updateCurrentMazeIndex(nextMaze);
        }
    }
}
