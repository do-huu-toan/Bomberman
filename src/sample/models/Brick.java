package sample.models;

import javafx.scene.image.Image;
import sample.view.Main;

public class Brick extends Object{
    private final static String urlImage = "sample/images/brick.png";
    private final static String brick = "sample/images/brick_exploded.png";
    private final static String brick1 = "sample/images/brick_exploded1.png";
    private final static String brick2 = "sample/images/brick_exploded2.png";

    Animation brickDelete;
    boolean isDelete = false;
    boolean chayHieuUng = false;
    long timeInit;
    long thoiGianChayHieuUng = 600;
    @Override
    public void update() {
        if(isDelete)
        {
            if(chayHieuUng == false)
            {
                chayHieuUng =true;
                timeInit = System.currentTimeMillis();
            }
            if(chayHieuUng)
            {
                this.img = brickDelete.getCurrentFrame(200);
                if(System.currentTimeMillis() - timeInit >= thoiGianChayHieuUng)
                {
                    Main.ObjectToChange.remove(this);
                }
            }

        }
    }

    public Brick(int x, int y)
    {
        super(x, y, new Image(urlImage));
        brickDelete = new Animation();
        brickDelete.add(new Image(brick));
        brickDelete.add(new Image(brick1));
        brickDelete.add(new Image(brick2));
    }
}
