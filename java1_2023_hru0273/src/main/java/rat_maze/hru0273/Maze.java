package rat_maze.hru0273;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Maze implements Drawable{

    private int[][] maze;
    private double cellWidth;
    private double cellHeight;
    int currentMazeIndex;

    public Maze(Canvas canvas, int[][] maze) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.maze = maze;
        cellWidth = gc.getCanvas().getWidth() / maze[0].length;
        cellHeight = gc.getCanvas().getHeight() / maze.length;
        this.currentMazeIndex = 0;
    }

    public List<Rectangle2D> getWallBoundingBoxes() {
        List<Rectangle2D> wallBoundingBoxes = new ArrayList<>();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 1) {
                    double x = j * cellWidth;
                    double y = i * cellHeight;
                    wallBoundingBoxes.add(new Rectangle2D(x, y, cellWidth, cellHeight));
                }
            }
        }

        return wallBoundingBoxes;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 1) {
                    gc.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }

}


