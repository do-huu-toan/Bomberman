package sample.models;

import javafx.scene.image.Image;

public class FrameItem extends Object{
    private final static String Brick = "sample/images/brick.png";
    private final static String FrameItem = "sample/images/flameItem.png";
    public boolean hienThiFrameItem = false;

    @Override
    public void update() {
        if(hienThiFrameItem == true) this.img = new Image(FrameItem);
    }

    public FrameItem(int x, int y)
    {
        super(x,y,new Image(Brick));
    }
}
