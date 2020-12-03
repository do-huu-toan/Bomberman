package sample.models;


import javafx.scene.image.Image;

public class Portal extends Object {
    public final static String Portal = "sample/images/portal.png";
    public final static String Brick = "sample/images/brick.png";
    public boolean hienThiPortal = false;

    @Override
    public void update() {
        if(hienThiPortal == true) this.img = new Image(Portal);
    }

    public Portal(int x, int y)
    {
        super(x,y,new Image(Brick));
    }
}
