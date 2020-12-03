package sample.models;

import javafx.scene.image.Image;
import sample.view.Main;

public class Bomb extends Object {
    private final static String Bomb = "sample/images/bomb.png";
    private final static String Bomb1 = "sample/images/bomb_1.png";
    private final static String Bomb2 = "sample/images/bomb_2.png";
    private long timeInit;
    private long thoiGianPhatNo = 2000;
    Animation bom;

    @Override
    public void update() {
        this.img = bom.getCurrentFrame(100);
        if(System.currentTimeMillis() - timeInit >= thoiGianPhatNo)
        {
            //System.out.println("Phát nổ");
            //Tạo vụ nổ:
            Explosion vuNo = new Explosion(this.x, this.y);
            Main.ObjectToChange.remove(this);
        }
    }

    public Bomb(int x, int y)
    {
        super(x, y,new Image(Bomb));
        timeInit = System.currentTimeMillis();
        bom = new Animation();
        bom.add(new Image(Bomb));
        bom.add(new Image(Bomb1));
        bom.add(new Image(Bomb2));
    }
}
