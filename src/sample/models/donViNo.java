package sample.models;

import javafx.scene.image.Image;
import sample.controllers.Controller;

public class donViNo extends Object {
    private final static String tam = "sample/images/bomb_exploded.png";
    private final static String tam1 = "sample/images/bomb_exploded1.png";
    private final static String tam2 = "sample/images/bomb_exploded2.png";

    private final static String trai = "sample/images/explosion_horizontal.png";
    private final static String trai1 = "sample/images/explosion_horizontal1.png";
    private final static String trai2 = "sample/images/explosion_horizontal2.png";

    private final static String traiCuoi = "sample/images/explosion_horizontal_left_last.png";
    private final static String traiCuoi1 = "sample/images/explosion_horizontal_left_last1.png";
    private final static String traiCuoi2 = "sample/images/explosion_horizontal_left_last2.png";

    private final static String phai = "sample/images/explosion_horizontal.png";
    private final static String phai1 = "sample/images/explosion_horizontal1.png";
    private final static String phai2 = "sample/images/explosion_horizontal2.png";

    private final static String phaiCuoi = "sample/images/explosion_horizontal_right_last.png";
    private final static String phaiCuoi1 = "sample/images/explosion_horizontal_right_last1.png";
    private final static String phaiCuoi2 = "sample/images/explosion_horizontal_right_last2.png";

    private final static String tren = "sample/images/explosion_vertical.png";
    private final static String tren1 = "sample/images/explosion_vertical1.png";
    private final static String tren2 = "sample/images/explosion_vertical2.png";

    private final static String trenCuoi = "sample/images/explosion_vertical_top_last.png";
    private final static String trenCuoi1 = "sample/images/explosion_vertical_top_last1.png";
    private final static String trenCuoi2 = "sample/images/explosion_vertical_top_last2.png";

    private final static String duoi = "sample/images/explosion_vertical.png";
    private final static String duoi1 = "sample/images/explosion_vertical1.png";
    private final static String duoi2 = "sample/images/explosion_vertical2.png";

    private final static String duoiCuoi = "sample/images/explosion_vertical_down_last.png";
    private final static String duoiCuoi1 = "sample/images/explosion_vertical_down_last1.png";
    private final static String duoiCuoi2 = "sample/images/explosion_vertical_down_last2.png";

    private int no;
    private Animation animation;

    public void update() {
        this.img = animation.getCurrentFrame(200);
    }

    /*
    0 : Tâm nổ
    1: Trái
    2: Trái cuối
    3: Phải
    4: Phải cuối
    5: Trên
    6: Trên cuối
    7: Dưới
    8: Dưới cuối
     */

    public donViNo(int x, int y, int no){
        super(x,y, Controller.SCALESIZE-5, Controller.SCALESIZE-5);
        this.no = no;
        animation = new Animation();
        if(no == 0) {
            this.img = new Image(tam);
            animation.add(new Image(tam));
            animation.add(new Image(tam1));
            animation.add(new Image(tam2));
        }
        else if(no == 1) {
            this.img = new Image(trai);
            animation.add(new Image(trai));
            animation.add(new Image(trai1));
            animation.add(new Image(trai2));
        }
        else if(no == 2) {
            this.img = new Image(traiCuoi);
            animation.add(new Image(traiCuoi));
            animation.add(new Image(traiCuoi1));
            animation.add(new Image(traiCuoi2));
        }
        else if(no == 3) {
            this.img = new Image(phai);
            animation.add(new Image(phai));
            animation.add(new Image(phai1));
            animation.add(new Image(phai2));
        }
        else if(no == 4) {
            this.img = new Image(phaiCuoi);
            animation.add(new Image(phaiCuoi));
            animation.add(new Image(phaiCuoi1));
            animation.add(new Image(phaiCuoi2));
        }
        else if(no == 5) {
            this.img = new Image(tren);
            animation.add(new Image(tren));
            animation.add(new Image(tren1));
            animation.add(new Image(tren2));
        }
        else if(no == 6) {
            this.img = new Image(trenCuoi);
            animation.add(new Image(trenCuoi));
            animation.add(new Image(trenCuoi1));
            animation.add(new Image(trenCuoi2));
        }
        else if(no == 7) {
            this.img = new Image(duoi);
            animation.add(new Image(duoi));
            animation.add(new Image(duoi1));
            animation.add(new Image(duoi2));
        }
        else if(no == 8) {
            this.img = new Image(duoiCuoi);
            animation.add(new Image(duoiCuoi));
            animation.add(new Image(duoiCuoi1));
            animation.add(new Image(duoiCuoi2));
        }
        System.out.println("Tạo vụ nổ");
    }

}
