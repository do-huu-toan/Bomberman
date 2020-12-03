package sample.models;

import javafx.scene.image.Image;
import sample.controllers.Controller;
import sample.view.Main;


public class Bomber extends Object {
    private boolean isDelete = false;
    public boolean isDie = false;
    private long timeDead;
    @Override
    public void update() {
        //Xử lý va chạm:
        for (Object i:Main.ObjectToChange) {
            if(i instanceof Balloom)
            {
                if(this.collision(i))
                {
                    this.isDie = true;
                    break;
                }
            }
            else continue;
        }
        if(isDie)
        {
            if(!isDelete)
            {
                isDelete = true;
                timeDead = System.currentTimeMillis();
            }
            else
            {
                this.img = player_dead.getCurrentFrame(300);
                if(System.currentTimeMillis() - timeDead >= 900)
                {
                    Controller.GAME_OVER = true;
                    Main.ObjectToChange.remove(this);
                }
            }
        }
        else
        {
            if(!collinsMap())
            {
                this.x += moveStepX;
                this.y += moveStepY;
            }
        }
    }

    //Check va chạm với Brick và Wall
    public boolean collinsMap()
    {
        //System.out.println("Toa do: "+x+","+y);
        boolean collinsMap = false;
        for(Object o: Main.staticObject)
        {
            if(o instanceof Wall)
            {
                if(this.collision(o))
                {
                    System.out.println("Va cham");
                    if(trangThai.equals(Huong.LEN)) this.y += STEP;
                    if(trangThai.equals(Huong.XUONG))this.y -= STEP;
                    if(trangThai.equals(Huong.PHAI)) this.x -=  STEP;
                    if(trangThai.equals(Huong.TRAI)) this.x += STEP;
                    collinsMap = true;
                    break;
                }
            }

            else continue;
        }
        for (Object o:Main.ObjectToChange)
        {
            if(o instanceof Brick)
            {
                if(this.collision(o))
                {
                    System.out.println("Va cham");
                    if(trangThai.equals(Huong.LEN)) this.y += STEP;
                    if(trangThai.equals(Huong.XUONG))this.y -= STEP;
                    if(trangThai.equals(Huong.PHAI)) this.x -=  STEP;
                    if(trangThai.equals(Huong.TRAI)) this.x += STEP;
                    collinsMap = true;
                    break;
                }
            }
            //Nếu portal chưa bị phá hủy
            else if(o instanceof Portal)
            {
                if(this.collision(o))
                {
                    if(((Portal) o).hienThiPortal == false)
                    {
                        System.out.println("Va cham");
                        if(trangThai.equals(Huong.LEN)) this.y += STEP;
                        if(trangThai.equals(Huong.XUONG))this.y -= STEP;
                        if(trangThai.equals(Huong.PHAI)) this.x -=  STEP;
                        if(trangThai.equals(Huong.TRAI)) this.x += STEP;
                        collinsMap = true;
                        break;
                    }
                    else if(((Portal) o).hienThiPortal == true)
                    {
                        //Kiểm tra Balloom đã bị tiêu diệt hết chưa và qua màn
                        System.out.println(Controller.bot);
                        if(Controller.bot == 0)
                        {
                            System.out.println("Qua màn");
                            playSound.play_anPortal();
                            Controller.LEVEL_NOW++;
                            if(Controller.LEVEL_NOW > Controller.TONG_SO_LEVEL)
                            {
                                Controller.PHA_DAO = true;
                            }

                        }
                        else
                        {
                            //Không thể đi vào
                            System.out.println("Va cham");
                            if(trangThai.equals(Huong.LEN)) this.y += STEP;
                            if(trangThai.equals(Huong.XUONG))this.y -= STEP;
                            if(trangThai.equals(Huong.PHAI)) this.x -=  STEP;
                            if(trangThai.equals(Huong.TRAI)) this.x += STEP;
                            collinsMap = true;
                            break;
                        }
                    }
                }
            }
            else if(o instanceof FrameItem)
            {
                if(this.collision(o))
                {
                    if(((FrameItem) o).hienThiFrameItem == true)
                    {
                        //Nếu ăn thì xóa FramItem đi và tăng phạm vi nổ
                        playSound.play_anItem();
                        Main.ObjectToChange.remove(o);
                        Controller.kichThuocVuNo++;


                    }
                    else
                    {
                        System.out.println("Va cham Flame");

                        if(trangThai.equals(Huong.LEN)) this.y += STEP;
                        if(trangThai.equals(Huong.XUONG))this.y -= STEP;
                        if(trangThai.equals(Huong.PHAI)) this.x -=  STEP;
                        if(trangThai.equals(Huong.TRAI)) this.x += STEP;
                        collinsMap = true;
                        break;

                    }
                }

            }
            else continue;
        }
        return collinsMap;
    }

    enum Huong {
        DUNGIM, PHAI, TRAI, LEN, XUONG;
    }

    private final int STEP = Controller.SCALESIZE /8;
    private final static String bomberRigh = "sample/images/player_right.png";
    private final static String bomberRigh1 = "sample/images/player_right_1.png";
    private final static String bomberRigh2 = "sample/images/player_right_2.png";

    private final static String bomberLeft = "sample/images/player_left.png";
    private final static String bomberLeft1 = "sample/images/player_left_1.png";
    private final static String bomberLeft2 = "sample/images/player_left_2.png";

    private final static String bomberUp = "sample/images/player_up.png";
    private final static String bomberUp1 = "sample/images/player_up_1.png";
    private final static String bomberUp2 = "sample/images/player_up_2.png";

    private final static String bomberDown = "sample/images/player_down.png";
    private final static String bomberDown1 = "sample/images/player_down_1.png";
    private final static String bomberDown2 = "sample/images/player_down_2.png";

    private final static String bomberDead = "sample/images/player_dead1.png";
    private final static String bomberDead1 = "sample/images/player_dead2.png";
    private final static String bomberDead2 = "sample/images/player_dead3.png";

    Animation player_right;
    Animation player_left;
    Animation player_up;
    Animation player_down;
    Animation player_dead;

    Huong trangThai = Huong.DUNGIM;

    public int moveStepX, moveStepY;

    public Bomber(int x, int y) {
        super(x, y, new Image(bomberRigh),Controller.SCALESIZE-4, Controller.SCALESIZE-3);

        moveStepX = 0;
        moveStepY = 0;

        player_right = new Animation();
        player_right.add(new Image(bomberRigh));
        player_right.add(new Image(bomberRigh1));
        player_right.add(new Image(bomberRigh2));

        player_left = new Animation();
        player_left.add(new Image(bomberLeft));
        player_left.add(new Image(bomberLeft1));
        player_left.add(new Image(bomberLeft2));

        player_up = new Animation();
        player_up.add(new Image(bomberUp));
        player_up.add(new Image(bomberUp1));
        player_up.add(new Image(bomberUp2));

        player_down = new Animation();
        player_down.add(new Image(bomberDown));
        player_down.add(new Image(bomberDown1));
        player_down.add(new Image(bomberDown2));

        player_dead = new Animation();
        player_dead.add(new Image(bomberDead));
        player_dead.add(new Image(bomberDead1));
        player_dead.add(new Image(bomberDead2));
    }


    public void thaBom()
    {
        System.out.println("Thả bom");
        Bomb b = new Bomb(this.y,this.x);
        Main.ObjectToChange.add(b);
    }
    public void Tien() {
        moveStepX = STEP;
        trangThai = Huong.PHAI;
        this.img = player_right.getCurrentFrame();
    }

    public void Lui() {
        moveStepX = -STEP;
        trangThai = Huong.TRAI;
        ;
        this.img = player_left.getCurrentFrame();
    }

    public void Len() {
        moveStepY = -STEP;
        trangThai = Huong.LEN;
        this.img = player_up.getCurrentFrame();
    }

    public void Xuong() {
        moveStepY = STEP;
        trangThai = Huong.XUONG;
        this.img = player_down.getCurrentFrame();
    }

}

