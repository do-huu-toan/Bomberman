package sample.models;

import javafx.scene.canvas.GraphicsContext;
import sample.controllers.Controller;
import sample.view.Main;

import java.util.ArrayList;

public class Explosion extends Object {
    private final static String bombExplosion = "main/java/sample/images/bomb_exploded.png";
    private final static String bomExplosion1 = "main/java/sample/images/bomb_exploded1.png";
    private final static String bomExplosion2 = "main/java/sample/images/bomb_exploded2.png";

    private ArrayList<donViNo> listDonViNo = new ArrayList<donViNo>();
    private long timeInit;
    private long thoiGianNo = 600;

    private boolean khoiTaoTrai = true;
    private boolean khoiTaoPhai = true;
    private boolean khoiTaoTren = true;
    private boolean khoiTaoDuoi = true;


    private GraphicsContext gc;
    @Override
    public void render(GraphicsContext gc) {
        for (donViNo i:listDonViNo) {
            i.render(gc);
        }
    }

    public void update() {
        for (donViNo i:listDonViNo) {
            for (Object j:Main.ObjectToChange) {
                if(j instanceof Bomber)
                {
                    if(i.collision(j))((Bomber) j).isDie = true;
                }
                else if(j instanceof Balloom)
                {
                    if(i.collision(j))((Balloom) j).isDie = true;
                }
                else if(j instanceof Portal)
                {
                    if(i.collision(j))((Portal) j).hienThiPortal = true;
                }
                else if(j instanceof FrameItem)
                {
                    if(i.collision(j))((FrameItem) j).hienThiFrameItem = true;
                }
            }
            i.update();
        }
        if(System.currentTimeMillis() - timeInit >= thoiGianNo)
        {
            for (donViNo i:listDonViNo) {
                Main.Explosion.remove(this);
                //Main.ObjectToChange.remove(i);
                //Main.ObjectToChange.remove(this);
            }
        }
    }

    public Explosion(int x, int y)
    {
        super(x,y);
        timeInit = System.currentTimeMillis();
        donViNo tam = new donViNo(x,y,0);
        listDonViNo.add(tam);
        //Main.ObjectToChange.add(tam);
        for(int i = 1;i<=Controller.kichThuocVuNo;i++)
        {
            if(i == Controller.kichThuocVuNo)
            {
                //Tạo ngọn:
                //Kiểm tra nếu va chạm tường thì không add vào list
                if(khoiTaoTrai)
                {
                    donViNo traiLast = new donViNo(x-Controller.SCALESIZE*i,y,2);
//
                        Object collins = collinsWith(traiLast);
                        if(collins instanceof Wall){khoiTaoTrai = false;}
                        else
                        {
                            if(collins instanceof Brick)
                            {
                                System.out.println("Va cham Tuong");
                                ((Brick) collins).isDelete = true;
                            }
                            listDonViNo.add(traiLast);
                        }
                }
                if(khoiTaoPhai)
                {
                    donViNo phaiLast = new donViNo(x+Controller.SCALESIZE*i,y,4);
                    Object collins = collinsWith(phaiLast);
                    if(collins instanceof Wall){khoiTaoPhai = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(phaiLast);
                    }
                }
                if(khoiTaoTren)
                {
                    donViNo trenLast = new donViNo(x, y-Controller.SCALESIZE*i, 6);
                    Object collins = collinsWith(trenLast);

                    if(collins instanceof Wall){khoiTaoTren = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(trenLast);
                    }
                }
                if(khoiTaoDuoi)
                {
                    donViNo duoiLast = new donViNo(x,y+Controller.SCALESIZE*i,8);
                    Object collins = collinsWith(duoiLast);

                    if(collins instanceof Wall){khoiTaoDuoi = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(duoiLast);
                    }
                }
            }
            else
            {

                if(khoiTaoTrai)
                {
                    donViNo trai = new donViNo(x-Controller.SCALESIZE*i,y,1);
                    Object collins = collinsWith(trai);
                    if(collins instanceof Wall){khoiTaoTrai = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(trai);
                    }
                }
                if(khoiTaoPhai)
                {
                    donViNo phai = new donViNo(x+Controller.SCALESIZE*i,y,3);
                    Object collins = collinsWith(phai);
                    if(collins instanceof Wall){khoiTaoPhai = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(phai);
                    }
                }
                if(khoiTaoTren)
                {
                    donViNo tren = new donViNo(x, y-Controller.SCALESIZE*i, 5);
                    Object collins = collinsWith(tren);
                    if(collins instanceof Wall){khoiTaoTren = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(tren);
                    }
                }
                if(khoiTaoDuoi)
                {
                    donViNo duoi = new donViNo(x,y+Controller.SCALESIZE*i,7);
                    Object collins = collinsWith(duoi);
                    if(collins instanceof Wall){khoiTaoDuoi = false;}
                    else
                    {
                        if(collins instanceof Brick)
                        {
                            System.out.println("Va cham Tuong");
                            ((Brick) collins).isDelete = true;
                        }
                        listDonViNo.add(duoi);
                    }
                }
            }
        }
        Main.Explosion.add(this);
        //Main.ObjectToChange.add(this);
        System.out.println("Khởi tạo thành công");
    }
    public boolean kiemTraVaChamTuong(Object o1)
    {
        boolean result = false;
        for (Object i:Main.staticObject) {
            if(i instanceof Wall)
            {
                if(o1.collision(i))
                {
                    result = true;
                }
            }
            else continue;

        }
        return result;
    }
    public Object collinsWith(Object o)
    {
        Object result = null;
        for (Object i: Main.ObjectToChange) {
            if(o.collision(i))
            {
                result = i;
                return result;
            }
        }
        for(Object i: Main.staticObject)
        {
            if(o.collision(i))
            {
                if(i instanceof Wall)
                {
                    result = i;
                    return result;
                }
            }
        }
        return result;
    }
}

