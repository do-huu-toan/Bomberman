package sample.models;

import javafx.scene.image.Image;
import sample.controllers.Controller;
import sample.view.Main;

import java.util.Random;

public class Balloom extends Object {
    private final static String balloomLeft = "sample/images/balloom_left1.png";
    private final static String balloomLeft1 = "sample/images/balloom_left2.png";
    private final static String balloomLeft2 = "sample/images/balloom_left3.png";

    private final static String balloomRight = "sample/images/balloom_right1.png";
    private final static String balloomRight1 = "sample/images/balloom_right2.png";
    private final static String balloomRight2 = "sample/images/balloom_right3.png";

    private final static String balloomDown = "sample/images/balloom_left1.png";
    private final static String balloomDown1 = "sample/images/balloom_left2.png";
    private final static String balloomDown2 = "sample/images/balloom_left3.png";

    private final static String balloomUp = "sample/images/balloom_right1.png";
    private final static String balloomUp1 = "sample/images/balloom_right2.png";
    private final static String balloomUp2 = "sample/images/balloom_right3.png";

    private final static String balloomDead = "sample/images/balloom_dead.png";
    private final static String balloomDead0 = "sample/images/mob_dead1.png";
    private final static String balloomDead1 = "sample/images/mob_dead2.png";
    private final static String balloomDead2 = "sample/images/mob_dead3.png";

    private final static int STEP = 1;
    Animation aniBalloomLeft;
    Animation aniBalloomRight;
    Animation aniBalloomDown;
    Animation aniBalloomUp;
    Animation aniBalloomDead;

    public boolean isDie = false;
    private boolean isDelete = false;
    private long timeDead;

    private int huong = 0;
    @Override
    public void update() {
        if(isDie)
        {
            if(isDelete == false)
            {
                isDelete = true;
                timeDead = System.currentTimeMillis();//Lưu lại thời gian lúc bắt đầu va chạm chết để xử lý Animation
            }
            else if(isDelete)
            {
                this.img = aniBalloomDead.getCurrentFrame(250);
                if(System.currentTimeMillis() - timeDead >= 1000) //xóa Object
                {
                    Controller.bot--;
                    Main.ObjectToChange.remove(this);
                }
            }

        }
        else
        {
            if(huong == 0)
            {
                if(!checkVaChamWalBrickBomb())
                {
                    moveLeft();
                    this.img = aniBalloomLeft.getCurrentFrame(100);
                }
                else
                {
                    System.out.println("Va chạm đổi hướng");
                    moveRight();
                    randomHuong();
                }
            }
            else if(huong == 1)
            {
                if(!checkVaChamWalBrickBomb())
                {
                    moveRight();
                    this.img = aniBalloomRight.getCurrentFrame(100);
                }
                else
                {
                    moveLeft();
                    randomHuong();
                }
            }

            else if(huong == 2)
            {
                if(!checkVaChamWalBrickBomb())
                {
                    moveUp();
                    this.img = aniBalloomUp.getCurrentFrame(100);
                }
                else
                {
                    moveDown();
                    randomHuong();
                }

            }
            else if(huong == 3)
            {
                if(!checkVaChamWalBrickBomb())
                {
                    moveDown();
                    this.img = aniBalloomDown.getCurrentFrame(100);
                }
                else
                {
                    moveUp();
                    randomHuong();
                }
            }
        }

        /*
            Random integer:
            0: Trái
            1: Phải
            2: Trên
            3: Xuống
         */

    }
    public void moveLeft()
    {
        this.x -= STEP;
    }
    public void moveRight()
    {
        this.x += STEP;
    }
    public void moveUp()
    {
        this.y -= STEP;
    }
    public void moveDown()
    {
        this.y += STEP;
    }

    public boolean checkVaChamWalBrickBomb()
    {
        for (Object i: Main.staticObject) {
            if(i instanceof Wall)
            {
                if(this.collision(i))
                {
                    return true;
                }
            }
        }
        for (Object i:Main.ObjectToChange) {
            if(i instanceof Brick || i instanceof Bomb)
            {
                if(this.collision(i))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomHuong()
    {
        Random rd = new Random();
        huong = rd.nextInt(4);
    }

    public Balloom(int x, int y){
        super(x,y,new Image(balloomLeft),Controller.SCALESIZE, Controller.SCALESIZE);

        aniBalloomLeft = new Animation();
        aniBalloomLeft.add(new Image(balloomLeft));
        aniBalloomLeft.add(new Image(balloomLeft1));
        aniBalloomLeft.add(new Image(balloomLeft2));

        aniBalloomRight = new Animation();
        aniBalloomRight.add(new Image(balloomRight));
        aniBalloomRight.add(new Image(balloomRight1));
        aniBalloomRight.add(new Image(balloomRight2));

        aniBalloomDown = new Animation();
        aniBalloomDown.add(new Image(balloomDown));
        aniBalloomDown.add(new Image(balloomDown1));
        aniBalloomDown.add(new Image(balloomDown2));

        aniBalloomUp = new Animation();
        aniBalloomUp.add(new Image(balloomUp));
        aniBalloomUp.add(new Image(balloomUp1));
        aniBalloomUp.add(new Image(balloomUp2));

        aniBalloomDead = new Animation();
        aniBalloomDead.add(new Image(balloomDead));
        aniBalloomDead.add(new Image(balloomDead0));
        aniBalloomDead.add(new Image(balloomDead1));
        aniBalloomDead.add(new Image(balloomDead2));
    }
}
