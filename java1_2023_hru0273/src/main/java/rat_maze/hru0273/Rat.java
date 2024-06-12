package rat_maze.hru0273;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import org.example.Collisionable;

import  java.net.URL;

import java.io.File;
import java.util.List;

public class Rat implements Collisionable,Drawable {
    //private final double sizeheight = 30;
    //private final double sizewidth = 70;

    private final double canvasWidth;
    private final double canvasHeight;
    private Point2D position;
    private GraphicsContext gc;
    private Point2D previousPosition;
    private Point2D newPosition;
    private MazeController mazeController;
    private Maze[] mazes;
    private Maze currentMaze;
    private Cheese currentCheese;
    private int currentMazeIndex;
    private Cheese[] cheeses;
    private  Game game;
    private  Dir pos;


    public Rat(Canvas canvas, Game game) {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        position = new Point2D(canvasWidth / 2 , canvasHeight / 2);
        mazes = MazeInitializer.initializeMazes(canvas);
        cheeses = MazeInitializer.initializeCheeses(canvas,game);
        currentCheese = cheeses[currentMazeIndex];
        currentMaze = mazes[currentMazeIndex];
        currentMazeIndex = 0;
        this.mazeController = new MazeController(canvas, mazes,cheeses);
        pos =  Dir.left;
    }
    @Override
    public void draw(GraphicsContext gc) {

        switch (pos)
        {
            case left:
            {
                gc.drawImage(Constants.RAT_IMAGE, position.getX(), position.getY(), getRatWidth(), getRatHeight());
                break;
            }
            case right:
            {
                gc.drawImage(Constants.RAT_IMAGE_R, position.getX(), position.getY(), getRatWidth(), getRatHeight());
                break;
            }
            case up:
            {
                gc.drawImage(Constants.RAT_IMAGE_UP, position.getX(), position.getY(), getRatWidth(), getRatHeight());
                break;
            }
            case down:
            {
                gc.drawImage(Constants.RAT_IMAGE_DN, position.getX(), position.getY(), getRatWidth(), getRatHeight());
                break;
            }
        }

    }

    public void move(KeyCode keyPressed) {
        double stepSize = 10.0;

        previousPosition = position;
        newPosition = getPoint2D(keyPressed, stepSize);

        position = newPosition;

        if (!collidesWithMaze(currentMaze)) {
            position = newPosition;
        } else {
            position = previousPosition;
        }

    }


    private Point2D getPoint2D(KeyCode keyPressed, double stepSize) {
        double newX = position.getX();
        double newY = position.getY();

        switch (keyPressed) {
            case W:
                newY = newY - stepSize;

                //pos = Dir.up;
                break;
            case A:
                newX = newX - stepSize;

                pos  =Dir.left;
                break;
            case S:
                newY = newY + stepSize;

                //pos = Dir.down;
                break;
            case D:
                newX = newX + stepSize;

                pos = Dir.right;
                break;
            default:
                break;
        }

        return new Point2D(newX, newY);
    }

    private boolean collidesWithMaze(Maze maze) {
        Rectangle2D ratBoundingBox = getBoundingBox();

        List<Rectangle2D> mazeBoundingBoxes = maze.getWallBoundingBoxes();
        for (Rectangle2D mazeBoundingBox : mazeBoundingBoxes) {
            if (ratBoundingBox.intersects(mazeBoundingBox)) {

                return true;
            }
        }

        return false;
    }

    public enum Dir
    {
        left,
        right,
        up,
        down
    }

    @Override
    public Rectangle2D getBoundingBox() {
        Point2D newPos = getNewPosition();

        if (newPos != null) {
            double x = newPos.getX();
            double y = newPos.getY();

            return new Rectangle2D(x, y, getRatWidth(), getRatHeight() );
        }
        else {
                return new Rectangle2D(0, 0, getRatWidth(), getRatHeight());
        }
    }

    @Override
    public void hitBy(Collisionable other) {
        if (other instanceof Maze) {
            Maze maze = (Maze) other;
            if (collidesWithMaze(maze)) {
                position = previousPosition;
            }
        }
    }

    public Point2D getPreviousPosition() {
        return previousPosition;
    }

    public Point2D getPosition() {
        return position;
    }
   public void setPosition(Point2D position) {
        this.position = position;
    }
   public double getRatWidth(){
        switch (pos) {
            default:
            case left:
                return sizewidth();
            case right:
                return sizewidth();
            case up:
                return sizeheight();
            case down:
                return sizeheight();
        }
    }
    public double getRatHeight(){
        switch (pos) {
            default:
            case left:
                return sizeheight();
            case right:
                return sizeheight();
            case up:
                return sizewidth();
            case down:
                return sizewidth();
        }
    }

    private int sizewidth()
    {
        return 70;
    }

    private int sizeheight()
    {
        return 30;
    }

    public Point2D getNewPosition() {
        return newPosition;
    }

    public void setCurrentMazeIndex(int mazeIndex) {
        this.currentMazeIndex = mazeIndex;
        this.currentMaze = mazes[mazeIndex];
    }

    public int getCurrentMazeIndex(){
        return currentMazeIndex;
    }
}
