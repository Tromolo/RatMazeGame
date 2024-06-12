package rat_maze.hru0273;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DrawingThread extends AnimationTimer {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private long lasttime = -1;
    private Game game;

    public DrawingThread(Canvas canvas, Game game) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        this.game = game;
    }

    @Override
    public void handle(long now) {
            game.draw(gc);
            if (lasttime > 0) {
                game.simulate();

            }
            lasttime = now;
        }

    public Game getGame() {
        return game;
    }
}


