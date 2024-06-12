package rat_maze.hru0273;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.example.Collisionable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static rat_maze.hru0273.MazeInitializer.initializeCheeses;

public class Cheese implements Collisionable,Drawable{
    private double cheeseWidth;
    private double cheeseHeight;
    private int[][] Cheese;
    private double cellWidth;
    private double cellHeight;
    private boolean eaten;
    int currentMazeIndex;
    private Game game;


    public Cheese(Canvas canvas, int[][] Cheese, Game myGame) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.Cheese = Cheese;
        cellWidth = gc.getCanvas().getWidth() / Cheese[0].length;
        cellHeight = gc.getCanvas().getHeight() / Cheese.length;
        cheeseWidth = cellWidth /2;
        cheeseHeight = cellHeight / 2;
        eaten = false;
        this.currentMazeIndex = 0;
        game = myGame;
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (!isEaten()) {
            for (int i = 0; i < Cheese.length; i++) {
                for (int j = 0; j < Cheese[i].length; j++) {
                    if (Cheese[i][j] == 2) {
                        gc.drawImage(Constants.CHEESE_IMAGE, j * cellWidth, i * cellHeight, cheeseWidth, cheeseHeight);
                    }
                }
            }
        }
    }

    @Override
    public Rectangle2D getBoundingBox() {
        double x = 0;
        double y = 0;

        for (int i = 0; i < Cheese.length; i++) {
            for (int j = 0; j < Cheese[i].length; j++) {
                if (Cheese[i][j] == 2) {
                    x = j * cellWidth;
                    y = i * cellHeight;
                    return new Rectangle2D(x, y, cheeseWidth, cheeseHeight);
                }
            }
        }

        return new Rectangle2D(x, y, cheeseWidth, cheeseHeight);
    }

    @Override
    public void hitBy(Collisionable other) {
        if (other instanceof Rat && !isEaten()) {
            Rat rat = (Rat) other;
                if (collidesWithRat(rat)) {
                    setEaten(true);
                    game.increaseScore(10 );
                }
        }
    }
    public boolean collidesWithRat(Rat rat) {

        Rectangle2D ratBoundingBox = rat.getBoundingBox();
        Rectangle2D cheeseBoundingBox = getBoundingBox();

        return ratBoundingBox.intersects(cheeseBoundingBox);
    }

    public int getCurrentMazeIndex() {
        return currentMazeIndex;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public void updateCurrentMazeIndex(int newMazeIndex) {
        this.currentMazeIndex = newMazeIndex;
    }
}
