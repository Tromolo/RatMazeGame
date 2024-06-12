package rat_maze.hru0273;

import javafx.scene.image.Image;

public final class Constants {

    private Constants() {}

    public static final Image RAT_IMAGE;
    public static final Image RAT_IMAGE_R;
    public static final Image RAT_IMAGE_UP;
    public static final Image RAT_IMAGE_DN;
    public static final Image CHEESE_IMAGE;

    static{
        RAT_IMAGE = new Image(Constants.class.getResourceAsStream("rat.png"));
        RAT_IMAGE_R = new Image(Constants.class.getResourceAsStream("ratR.png"));
        RAT_IMAGE_UP = new Image(Constants.class.getResourceAsStream("ratUP.png"));
        RAT_IMAGE_DN = new Image(Constants.class.getResourceAsStream("ratDN.png"));

        CHEESE_IMAGE = new Image(Constants.class.getResourceAsStream("cheese.png"));
    }
}
